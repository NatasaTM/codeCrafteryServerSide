package com.codecraftery.Code.craftery.server.side.repository;

import com.codecraftery.Code.craftery.server.side.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Natasa Todorov Markovic
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
