package com.savstanis.exhibitionservice.exception;

public class InvalidExhibitionTitleException extends RuntimeException{
    public InvalidExhibitionTitleException() {
        super("Invalid title");
    }

    public InvalidExhibitionTitleException(String message) {
        super(message);
    }
}
