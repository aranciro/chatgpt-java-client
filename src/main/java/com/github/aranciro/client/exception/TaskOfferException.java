package com.github.aranciro.client.exception;

public final class TaskOfferException extends Exception {
    
    public TaskOfferException() {
    }
    
    public TaskOfferException(final String message) {
        super(message);
    }
    
    public TaskOfferException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public TaskOfferException(final Throwable cause) {
        super(cause);
    }
    
    public TaskOfferException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
