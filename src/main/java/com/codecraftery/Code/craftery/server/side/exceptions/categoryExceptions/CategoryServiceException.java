package com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions;

/**
 * @author Natasa Todorov Markovic
 */
public class CategoryServiceException extends Exception {
    public CategoryServiceException(String message) {
        super(message);
    }

    public CategoryServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
