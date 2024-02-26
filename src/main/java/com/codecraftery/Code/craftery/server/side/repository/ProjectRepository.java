package com.codecraftery.Code.craftery.server.side.repository;

import com.codecraftery.Code.craftery.server.side.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Natasa Todorov Markovic
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
