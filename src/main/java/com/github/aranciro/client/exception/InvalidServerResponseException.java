package com.github.aranciro.client.exception;

public class InvalidServerResponseException extends PromptException {
    public InvalidServerResponseException() {
    }
    
    public InvalidServerResponseException(final String message) {
        super(message);
    }
    
    public InvalidServerResponseException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public InvalidServerResponseException(final Throwable cause) {
        super(cause);
    }
    
    public InvalidServerResponseException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
