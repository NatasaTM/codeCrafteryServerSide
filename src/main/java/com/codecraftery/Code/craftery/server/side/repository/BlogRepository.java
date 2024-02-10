package com.codecraftery.Code.craftery.server.side.repository;

import com.codecraftery.Code.craftery.server.side.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
