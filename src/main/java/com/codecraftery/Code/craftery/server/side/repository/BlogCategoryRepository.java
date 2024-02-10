package com.codecraftery.Code.craftery.server.side.repository;

import com.codecraftery.Code.craftery.server.side.model.BlogCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogCategoryRepository extends JpaRepository<BlogCategory, Long> {
    BlogCategory findByName(String name);
}
