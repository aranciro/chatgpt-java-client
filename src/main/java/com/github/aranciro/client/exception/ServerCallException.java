package com.github.aranciro.client.exception;

public class ServerCallException extends PromptException {
    public ServerCallException() {
    }
    
    public ServerCallException(final String message) {
        super(message);
    }
    
    public ServerCallException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public ServerCallException(final Throwable cause) {
        super(cause);
    }
    
    public ServerCallException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
