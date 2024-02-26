package com.codecraftery.Code.craftery.server.side.exceptions.authenticationException;

/**
 * @author Natasa Todorov Markovic
 */
public class AuthenticationException extends Throwable {
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}


