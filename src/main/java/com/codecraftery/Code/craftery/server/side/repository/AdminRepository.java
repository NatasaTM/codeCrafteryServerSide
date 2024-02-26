package com.codecraftery.Code.craftery.server.side.repository;

import com.codecraftery.Code.craftery.server.side.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Natasa Todorov Markovic
 */
public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByEmail(String email);
}
