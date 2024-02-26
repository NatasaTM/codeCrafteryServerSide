package com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions;

/**
 * @author Natasa Todorov Markovic
 */
public class CategoryCreationException extends Exception {
    public CategoryCreationException(String message) {
        super(message);
    }

    public CategoryCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
