package com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions;

public class BlogServiceException extends Exception{
    public BlogServiceException(String message) {
        super(message);
    }

    public BlogServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
