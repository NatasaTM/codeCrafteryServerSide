package com.codecraftery.Code.craftery.server.side.service;

import com.codecraftery.Code.craftery.server.side.dto.AdminDto;
import com.codecraftery.Code.craftery.server.side.model.Admin;

import java.util.List;
import java.util.Optional;

/**
 * @author Natasa Todorov Markovic
 */
public interface AdminService {
    Admin saveAdmin(Admin admin);

    Admin updateAdmin(Admin admin);

    void deleteAdmin(Long id);

    Optional<Admin> findById(Long id);

    List<Admin> findAll();

    AdminDto getAdminDtoById(Long id);

    boolean existsByEmail(String email);


}
