package com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions;

public class BlogCreationException extends Exception {
    public BlogCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogCreationException(String message) {
        super(message);


    }
}
