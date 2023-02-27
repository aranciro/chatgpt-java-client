package com.github.aranciro.client.callback;

import com.github.aranciro.client.APIServerClient;
import com.github.aranciro.client.exception.InvalidServerResponseException;
import com.github.aranciro.client.exception.ServerCallException;
import com.github.aranciro.client.pojo.model.PromptResponse;
import lombok.NonNull;
import org.apache.commons.lang3.RandomStringUtils;

public class ConversationCallback extends AbstractConversationCallback {
    
    private static final String THREAD_NAME_PREFIX = "conv-callback-";
    
    @NonNull
    private final PromptCallback promptCallback;
    
    public ConversationCallback(@NonNull final APIServerClient client,
                                @NonNull final PromptCallback promptCallback) {
        super(client);
        this.promptCallback = promptCallback;
    }
    
    @Override
    public void onServerCallException(@NonNull final ServerCallException e) {
        Thread thread = new Thread(() -> this.promptCallback.onFailure(e), this.getThreadName());
        thread.start();
    }
    
    @Override
    public void onInvalidServerResponseException(@NonNull final InvalidServerResponseException e) {
        Thread thread = new Thread(() -> this.promptCallback.onFailure(e), this.getThreadName());
        thread.start();
    }
    
    @Override
    public void onPromptResponse(@NonNull final PromptResponse promptResponse) {
        Thread thread = new Thread(() -> this.promptCallback.onSuccess(promptResponse), this.getThreadName());
        thread.start();
    }
    
    @NonNull
    private String getThreadName() {
        return THREAD_NAME_PREFIX + RandomStringUtils.randomAlphabetic(8);
    }
    
}
