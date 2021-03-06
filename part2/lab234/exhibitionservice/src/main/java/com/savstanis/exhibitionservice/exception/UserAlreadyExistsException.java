package com.savstanis.exhibitionservice.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("User with this email already exists");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
