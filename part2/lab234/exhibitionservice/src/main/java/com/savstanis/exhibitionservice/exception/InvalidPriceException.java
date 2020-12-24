package com.savstanis.exhibitionservice.exception;

public class InvalidPriceException extends RuntimeException {
    public InvalidPriceException() {
        super("Invalid price");
    }

    public InvalidPriceException(String message) {
        super(message);
    }
}
