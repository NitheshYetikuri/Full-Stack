package com.furniturestore.exception;

@SuppressWarnings("serial")
public class RegistrationException extends RuntimeException {
    public RegistrationException(String message) {
        super(message);
    }
}