package com.github.aranciro.client.callback;

import com.github.aranciro.client.APIServerClient;
import com.github.aranciro.client.exception.InvalidServerResponseException;
import com.github.aranciro.client.exception.ServerCallException;
import com.github.aranciro.client.pojo.model.PromptResponse;
import lombok.NonNull;

import java.util.concurrent.CompletableFuture;

public class ConversationAsyncCallback extends AbstractConversationCallback {
    
    @NonNull
    private final CompletableFuture<PromptResponse> future;
    
    public ConversationAsyncCallback(@NonNull final APIServerClient client,
                                     @NonNull final CompletableFuture<PromptResponse> future) {
        super(client);
        this.future = future;
    }
    
    @Override
    public void onServerCallException(@NonNull final ServerCallException e) {
        this.future.completeExceptionally(e);
    }
    
    @Override
    public void onInvalidServerResponseException(@NonNull final InvalidServerResponseException e) {
        this.future.completeExceptionally(e);
    }
    
    @Override
    public void onPromptResponse(@NonNull final PromptResponse promptResponse) {
        this.future.complete(promptResponse);
    }
    
}
