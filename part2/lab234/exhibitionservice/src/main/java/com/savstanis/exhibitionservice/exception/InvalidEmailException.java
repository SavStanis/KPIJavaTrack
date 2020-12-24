package com.savstanis.exhibitionservice.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException() {
        super("Invalid email.");
    }

    public InvalidEmailException(String message) {
        super(message);
    }
}
