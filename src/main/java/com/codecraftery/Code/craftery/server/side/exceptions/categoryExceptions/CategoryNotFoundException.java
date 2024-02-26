package com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions;

/**
 * @author Natasa Todorov Markovic
 */
public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
