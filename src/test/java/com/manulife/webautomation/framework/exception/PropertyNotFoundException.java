package com.manulife.webautomation.framework.exception;

public class PropertyNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public PropertyNotFoundException() {
    }

    public PropertyNotFoundException(final String message) {
	super(message);
    }

    public PropertyNotFoundException(final String message, final Throwable cause) {
	super(message, cause);
    }
}
