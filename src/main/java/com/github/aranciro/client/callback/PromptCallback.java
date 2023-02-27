package com.github.aranciro.client.callback;

import com.github.aranciro.client.exception.PromptException;
import com.github.aranciro.client.pojo.model.PromptResponse;
import lombok.NonNull;

public interface PromptCallback {
    
    void onFailure(@NonNull final PromptException e);
    
    void onSuccess(@NonNull final PromptResponse response);
    
}
