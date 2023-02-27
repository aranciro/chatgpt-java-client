package com.github.aranciro.client;

import com.github.aranciro.client.callback.AbstractConversationCallback;
import com.github.aranciro.client.callback.ConversationAsyncCallback;
import com.github.aranciro.client.callback.ConversationCallback;
import com.github.aranciro.client.callback.PromptCallback;
import com.github.aranciro.client.exception.PromptException;
import com.github.aranciro.client.exception.PromptSchedulingException;
import com.github.aranciro.client.exception.TaskOfferException;
import com.github.aranciro.client.pojo.model.APIServer;
import com.github.aranciro.client.pojo.model.Conversation;
import com.github.aranciro.client.pojo.model.Prompt;
import com.github.aranciro.client.pojo.model.PromptResponse;
import com.github.aranciro.client.pojo.rest.request.ConversationRequest;
import com.github.aranciro.client.pojo.rest.response.ConversationResponse;
import com.squareup.moshi.JsonAdapter;
import lombok.Builder;
import lombok.NonNull;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public final class APIServerClient implements ChatGPTClient {
    
    private static final String ENDPOINT_CONVERSATION = "/conversation";
    
    @NonNull
    private final APIServer server;
    @NonNull
    private final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(60000, TimeUnit.MILLISECONDS)
            .build();
    @NonNull
    private final ThreadPoolExecutor executor;
    private final Conversation conversation = new Conversation();
    
    public APIServerClient() {
        this(new APIServer());
    }
    
    @Builder
    public APIServerClient(@NonNull final APIServer server) {
        this.server = server;
        this.executor = new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10)
        );
        this.executor.allowCoreThreadTimeOut(true);
    }
    
    @NonNull
    public PromptResponse sendPrompt(@NonNull final String prompt) throws PromptException {
        return this.sendPrompt(new Prompt(prompt));
    }
    
    @NonNull
    public PromptResponse sendPrompt(@NonNull final Prompt prompt) throws PromptException {
        try {
            return this.sendPromptAsync(prompt).get();
        } catch (PromptException e) {
            throw e;
        } catch (Exception e) {
            throw new PromptException("Unexpected error", e);
        }
    }
    
    @NonNull
    public CompletableFuture<PromptResponse> sendPromptAsync(@NonNull final String prompt) throws PromptException {
        return this.sendPromptAsync(new Prompt(prompt));
    }
    
    @NonNull
    public CompletableFuture<PromptResponse> sendPromptAsync(@NonNull final Prompt prompt) throws PromptException {
        CompletableFuture<PromptResponse> future = new CompletableFuture<>();
        try {
            this.submitPromptTask(prompt, new ConversationAsyncCallback(this, future));
        } catch (Exception e) {
            throw new PromptSchedulingException("Too many pending requests", e);
        }
        return future;
    }
    
    public void sendPromptWithCallback(@NonNull final String prompt,
                                       @NonNull final PromptCallback callback) throws PromptException {
        this.sendPromptWithCallback(new Prompt(prompt), callback);
    }
    
    public void sendPromptWithCallback(@NonNull final Prompt prompt,
                                       @NonNull final PromptCallback callback) throws PromptException {
        try {
            this.submitPromptTask(prompt, new ConversationCallback(this, callback));
        } catch (Exception e) {
            throw new PromptSchedulingException("Too many pending requests", e);
        }
    }
    
    @Synchronized
    public void updateConversation(@NonNull final ConversationResponse convResponse) {
        if (this.conversation.getId() == null) {
            this.conversation.setId(convResponse.getConversationId());
        }
        this.conversation.setLastMessageId(convResponse.getMessageId());
    }
    
    @Synchronized
    private void submitPromptTask(@NonNull final Prompt prompt, @NonNull final AbstractConversationCallback callback)
            throws TaskOfferException {
        Request request = this.buildRequest(prompt);
        Runnable promptTask = () -> this.client.newCall(request).enqueue(callback);
        this.submitTask(promptTask);
    }
    
    private void submitTask(@NonNull final Runnable task) throws TaskOfferException {
        if (!this.executor.getQueue().offer(task)) {
            throw new TaskOfferException("Thread pool queue is full");
        }
    }
    
    private Request buildRequest(@NonNull final Prompt prompt) {
        ConversationRequest req = new ConversationRequest(
                prompt.getText(),
                this.conversation.getId(),
                this.conversation.getLastMessageId());
        JsonAdapter<ConversationRequest> adapter = ClientUtils.getMoshi().adapter(ConversationRequest.class);
        String json = adapter.toJson(req);
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
        log.debug("REQUEST BODY: {}", json);
        return new Request.Builder()
                .url(this.getConversationUrl(this.server)) //TODO
                .post(body)
                .build();
    }
    
    private URL getConversationUrl(@NonNull final APIServer server) {
        URL url = null;
        try {
            //TODO https?
            url = new URL("http", server.getHost(), server.getPort(), ENDPOINT_CONVERSATION);
        } catch (MalformedURLException e) {
            log.error("Error while creating conversation endpoint URL", e);
        }
        return url;
    }
    
}
