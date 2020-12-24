package com.savstanis.exhibitionservice.exception;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException() {
        super("Invalid date was inputted");
    }

    public InvalidDateException(String message) {
        super(message);
    }
}
