package com.codecraftery.Code.craftery.server.side.repository;

import com.codecraftery.Code.craftery.server.side.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Natasa Todorov Markovic
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
