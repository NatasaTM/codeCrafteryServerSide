package com.codecraftery.Code.craftery.server.side.service;

import com.codecraftery.Code.craftery.server.side.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
    void addBlogCategory(Category category);
    Category findById(Long id);
    void deleteById(Long id);

    Category findByName(String name);
    List<Category> findListById(List<Long> ids);

}

