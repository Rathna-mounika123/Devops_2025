package com.example.exception;

public class CardDetailsNotFoundException extends RuntimeException{
	public CardDetailsNotFoundException(String message) {
        super(message);
    }
	public CardDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
