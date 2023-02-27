package com.github.aranciro.client;

import com.github.aranciro.client.callback.PromptCallback;
import com.github.aranciro.client.exception.PromptException;
import com.github.aranciro.client.pojo.model.Prompt;
import com.github.aranciro.client.pojo.model.PromptResponse;
import lombok.NonNull;

import java.util.concurrent.CompletableFuture;

public interface ChatGPTClient {
    
    @NonNull PromptResponse sendPrompt(@NonNull final String prompt) throws PromptException;
    
    @NonNull PromptResponse sendPrompt(@NonNull final Prompt prompt) throws PromptException;
    
    @NonNull CompletableFuture<PromptResponse> sendPromptAsync(@NonNull final String prompt) throws PromptException;
    
    @NonNull CompletableFuture<PromptResponse> sendPromptAsync(@NonNull final Prompt prompt) throws PromptException;
    
    void sendPromptWithCallback(@NonNull final String prompt,
                                @NonNull final PromptCallback callback) throws PromptException;
    
    void sendPromptWithCallback(@NonNull final Prompt prompt,
                                @NonNull final PromptCallback callback) throws PromptException;
    
}
