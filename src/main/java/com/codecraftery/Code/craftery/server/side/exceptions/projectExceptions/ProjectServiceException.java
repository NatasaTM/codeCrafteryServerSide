package com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions;

public class ProjectServiceException extends Exception {
    public ProjectServiceException(String message) {
        super(message);
    }

    public ProjectServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
