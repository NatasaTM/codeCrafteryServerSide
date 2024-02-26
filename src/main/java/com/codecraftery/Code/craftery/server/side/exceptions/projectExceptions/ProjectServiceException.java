package com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions;

/**
 * @author Natasa Todorov Markovic
 */
public class ProjectServiceException extends Exception {
    public ProjectServiceException(String message) {
        super(message);
    }

    public ProjectServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
