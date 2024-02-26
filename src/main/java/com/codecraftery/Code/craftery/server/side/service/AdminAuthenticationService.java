package com.codecraftery.Code.craftery.server.side.service;

import com.codecraftery.Code.craftery.server.side.exceptions.authenticationException.AuthenticationException;
import com.codecraftery.Code.craftery.server.side.model.Admin;

/**
 * @author Natasa Todorov Markovic
 */
public interface AdminAuthenticationService {
    Admin authenticate(String email, String password) throws AuthenticationException;
}
