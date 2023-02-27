package com.github.aranciro.client.exception;

public class ClientErrorException extends PromptException {
    public ClientErrorException() {
    }
    
    public ClientErrorException(final String message) {
        super(message);
    }
    
    public ClientErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public ClientErrorException(final Throwable cause) {
        super(cause);
    }
    
    public ClientErrorException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
