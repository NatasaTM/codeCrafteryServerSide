package com.codecraftery.Code.craftery.server.side.service;

import com.codecraftery.Code.craftery.server.side.model.BlogCategory;

import java.util.List;

public interface BlogCategoryService {

    List<BlogCategory> getAllBlogCategories();
    void addBlogCategory(BlogCategory blogCategory);
    BlogCategory findById(Long id);
    void deleteById(Long id);

    BlogCategory findByName(String name);

}
