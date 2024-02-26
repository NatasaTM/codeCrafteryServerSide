package com.codecraftery.Code.craftery.server.side.service.impl;

import com.codecraftery.Code.craftery.server.side.model.Admin;
import com.codecraftery.Code.craftery.server.side.service.AdminAuthenticationService;
import org.springframework.stereotype.Service;

/**
 * @author Natasa Todorov Markovic
 */
@Service
public class AdminAuthenticationServiceImpl implements AdminAuthenticationService {
    @Override
    public Admin authenticate(String email, String password) {
        return null;
    }
}
