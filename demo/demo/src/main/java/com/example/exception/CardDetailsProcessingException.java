package com.example.exception;
public class CardDetailsProcessingException extends RuntimeException {
    public CardDetailsProcessingException(String message) {
        super(message);
    }

    public CardDetailsProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}


