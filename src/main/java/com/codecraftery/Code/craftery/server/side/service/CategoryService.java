package com.codecraftery.Code.craftery.server.side.service;

import com.codecraftery.Code.craftery.server.side.model.Category;

import java.util.List;

public interface BlogCategoryService {

    List<Category> getAllBlogCategories();
    void addBlogCategory(Category blogCategory);
    Category findById(Long id);
    void deleteById(Long id);

    Category findByName(String name);

}
