package com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions;

/**
 * @author Natasa Todorov Markovic
 */
public class ProjectCreationException extends Exception {
    public ProjectCreationException(String message) {
        super(message);
    }

    public ProjectCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
