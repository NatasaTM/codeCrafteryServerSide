package com.codecraftery.Code.craftery.server.side.repository;

import com.codecraftery.Code.craftery.server.side.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

   // @Query("SELECT b.category_id, c.name FROM BlogCategory b JOIN Category c ON (c.id = b.category_id) WHERE b.blog_id = :blogId")
   // List<Category> findCategoriesByBlogId(@Param("blogId") Long blogId);



}
