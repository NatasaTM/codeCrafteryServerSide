package com.codecraftery.Code.craftery.server.side.service.impl;

import com.codecraftery.Code.craftery.server.side.model.Category;
import com.codecraftery.Code.craftery.server.side.repository.CategoryRepository;
import com.codecraftery.Code.craftery.server.side.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository blogCategoryRepository) {
        this.categoryRepository = blogCategoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void addBlogCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        Category blogCategory = categoryRepository.findById(id).get();
        return blogCategory;
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);

    }

    @Override
    public Category findByName(String name) {
        Category category = categoryRepository.findByName(name);
        return category;
    }

    @Override
    public List<Category> findListById(List<Long> ids) {

        return categoryRepository.findAllById(ids);
    }
}
