package com.codecraftery.Code.craftery.server.side.repository;

import com.codecraftery.Code.craftery.server.side.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Natasa Todorov Markovic
 */
public interface AdminRepository extends JpaRepository<Admin, String> {
}
