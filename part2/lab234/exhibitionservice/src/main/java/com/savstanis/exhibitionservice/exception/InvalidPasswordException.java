package com.savstanis.exhibitionservice.exception;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super("Password must contain at least 8 characters");
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
