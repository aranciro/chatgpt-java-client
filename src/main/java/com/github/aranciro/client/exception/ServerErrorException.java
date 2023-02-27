package com.github.aranciro.client.exception;

public class ServerErrorException extends PromptException {
    public ServerErrorException() {
    }
    
    public ServerErrorException(final String message) {
        super(message);
    }
    
    public ServerErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public ServerErrorException(final Throwable cause) {
        super(cause);
    }
    
    public ServerErrorException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
