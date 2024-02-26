package com.codecraftery.Code.craftery.server.side.exceptions.roleExceptions;

/**
 * @author Natasa Todorov Markovic
 */
public class RoleCreationException extends Exception{
    public RoleCreationException(String message) {
        super(message);
    }

    public RoleCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}


