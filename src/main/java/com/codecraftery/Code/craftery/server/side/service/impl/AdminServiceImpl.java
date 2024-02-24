package com.codecraftery.Code.craftery.server.side.service.impl;

import com.codecraftery.Code.craftery.server.side.dto.AdminDto;
import com.codecraftery.Code.craftery.server.side.model.Admin;
import com.codecraftery.Code.craftery.server.side.service.AdminAuthenticationService;
import com.codecraftery.Code.craftery.server.side.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final AdminAuthenticationService adminAuthenticationService;


    @Override
    public Admin saveAdmin(Admin admin) {
        return null;
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return null;
    }

    @Override
    public void deleteAdmin(Long id) {

    }

    @Override
    public Optional<Admin> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Admin> findAll() {
        return null;
    }

    @Override
    public AdminDto getAdminDtoById(Long id) {
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
