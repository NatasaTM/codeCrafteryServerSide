package com.codecraftery.Code.craftery.server.side.service.impl;

import com.codecraftery.Code.craftery.server.side.exceptions.authenticationException.AuthenticationException;
import com.codecraftery.Code.craftery.server.side.model.Admin;
import com.codecraftery.Code.craftery.server.side.repository.AdminRepository;
import com.codecraftery.Code.craftery.server.side.service.AdminAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Natasa Todorov Markovic
 */
@Service
@RequiredArgsConstructor
public class AdminAuthenticationServiceImpl implements AdminAuthenticationService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AdminAuthenticationServiceImpl.class);

    @Override
    public Admin authenticate(String email, String password) throws AuthenticationException {
        try {
            // Find admin by email
            Optional<Admin> potentialAdmin = adminRepository.findByEmail(email);
            if (potentialAdmin.isEmpty()) {
                throw new AuthenticationException("User with provided email not found");
            }

            Admin admin = potentialAdmin.get();

            // Compare hashed passwords using constant-time comparison
            if (passwordEncoder.matches(password, admin.getPassword())) {
                return admin; // Authentication successful
            } else {
                throw new AuthenticationException("Invalid password");
            }
        } catch (Exception e) {
            // Log authentication failure or unexpected errors
             logger.error("Authentication failed: " + e.getMessage());
            throw new AuthenticationException("Authentication failed: " + e.getMessage());
        }
    }
    }

