package com.furniturestore.exception;

@SuppressWarnings("serial")
public class ProductAdditionException extends RuntimeException {
    public ProductAdditionException(String message) {
        super(message);
    }
}
