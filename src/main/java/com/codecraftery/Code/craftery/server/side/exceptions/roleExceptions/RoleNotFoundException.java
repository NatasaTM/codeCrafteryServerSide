package com.codecraftery.Code.craftery.server.side.exceptions.roleExceptions;

/**
 * @author Natasa Todorov Markovic
 */
public class RoleNotFoundException extends Exception{
    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}


