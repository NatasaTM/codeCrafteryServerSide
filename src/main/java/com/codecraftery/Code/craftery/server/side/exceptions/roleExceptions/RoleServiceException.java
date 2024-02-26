package com.codecraftery.Code.craftery.server.side.exceptions.roleExceptions;

/**
 * @author Natasa Todorov Markovic
 */
public class RoleServiceException extends Exception{
    public RoleServiceException(String message) {
        super(message);
    }

    public RoleServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}


