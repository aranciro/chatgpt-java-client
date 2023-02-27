package com.github.aranciro.client.exception;

public class PromptSchedulingException extends PromptException {
    
    public PromptSchedulingException() {
    }
    
    public PromptSchedulingException(final String message) {
        super(message);
    }
    
    public PromptSchedulingException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public PromptSchedulingException(final Throwable cause) {
        super(cause);
    }
    
    public PromptSchedulingException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
