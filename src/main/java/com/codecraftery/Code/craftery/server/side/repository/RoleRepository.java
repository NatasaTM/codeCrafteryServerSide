package com.codecraftery.Code.craftery.server.side.repository;

import com.codecraftery.Code.craftery.server.side.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Natasa Todorov Markovic
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
