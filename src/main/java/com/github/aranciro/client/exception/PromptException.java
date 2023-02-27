package com.github.aranciro.client.exception;

public class PromptException extends Exception {
    public PromptException() {
    }
    
    public PromptException(final String message) {
        super(message);
    }
    
    public PromptException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public PromptException(final Throwable cause) {
        super(cause);
    }
    
    public PromptException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
