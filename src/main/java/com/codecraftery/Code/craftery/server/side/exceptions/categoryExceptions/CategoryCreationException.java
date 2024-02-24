package com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions;

public class CategoryCreationException extends Exception{
    public CategoryCreationException(String message) {
        super(message);
    }

    public CategoryCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
