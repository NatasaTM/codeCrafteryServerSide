package com.codecraftery.Code.craftery.server.side.service.impl;

import com.codecraftery.Code.craftery.server.side.model.BlogCategory;
import com.codecraftery.Code.craftery.server.side.repository.BlogCategoryRepository;
import com.codecraftery.Code.craftery.server.side.service.BlogCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {
    private BlogCategoryRepository blogCategoryRepository;

    public BlogCategoryServiceImpl(BlogCategoryRepository blogCategoryRepository) {
        this.blogCategoryRepository = blogCategoryRepository;
    }

    @Override
    public List<BlogCategory> getAllBlogCategories() {
        return blogCategoryRepository.findAll();
    }

    @Override
    public void addBlogCategory(BlogCategory blogCategory) {
        blogCategoryRepository.save(blogCategory);
    }

    @Override
    public BlogCategory findById(Long id) {
        BlogCategory blogCategory = blogCategoryRepository.findById(id).get();
        return blogCategory;
    }

    @Override
    public void deleteById(Long id) {
        blogCategoryRepository.deleteById(id);

    }

    @Override
    public BlogCategory findByName(String name) {
        BlogCategory category = blogCategoryRepository.findByName(name);
        return category;
    }
}
