package com.manulife.webautomation.framework.exception;

public class InvalidLocatorStrategyException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidLocatorStrategyException() {
    }

    public InvalidLocatorStrategyException(final String message) {
	super(message);
    }

    public InvalidLocatorStrategyException(final String message, final Throwable cause) {
	super(message, cause);
    }
}
