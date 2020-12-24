package com.savstanis.exhibitionservice.exception;

public class LoginException extends RuntimeException{
    public LoginException() {
        super("Invalid email or password");
    }

    public LoginException(String message) {
        super(message);
    }
}
