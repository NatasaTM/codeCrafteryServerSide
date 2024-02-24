package com.codecraftery.Code.craftery.server.side.service;

import com.codecraftery.Code.craftery.server.side.model.Admin;

public interface AdminAuthenticationService {
    Admin authenticate(String email, String password);
}
