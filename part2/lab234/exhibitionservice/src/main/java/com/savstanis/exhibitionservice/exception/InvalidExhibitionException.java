package com.savstanis.exhibitionservice.exception;

public class InvalidExhibitionException extends RuntimeException{
    public InvalidExhibitionException() {
        super("Invalid exhibition!");
    }

    public InvalidExhibitionException(String message) {
        super(message);
    }
}
