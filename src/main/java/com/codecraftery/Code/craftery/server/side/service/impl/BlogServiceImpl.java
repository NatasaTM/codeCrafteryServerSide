package com.codecraftery.Code.craftery.server.side.service.impl;

import com.codecraftery.Code.craftery.server.side.dto.BlogDto;
import com.codecraftery.Code.craftery.server.side.model.Blog;
import com.codecraftery.Code.craftery.server.side.repository.BlogRepository;
import com.codecraftery.Code.craftery.server.side.service.BlogService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Blog> getAllBlogs() throws IOException{
        List<Blog> blogs = blogRepository.findAll();


        return blogs;
    }

    @Override
    public Blog findById(Long id) {
        Blog blog = blogRepository.findById(id).get();
        return blog;
    }

    @Override
    public Blog addBlog(Blog blog) throws IOException {
        Blog _blog = blogRepository.save(blog);
        return _blog;
    }
/*
    @Override
    public BlogDto addBlog(BlogDto) {
      /*  try {
            if (imageFile != null && imageFile.exists()) {
                byte[] imageBytes = FileUtils.readFileToByteArray(imageFile);
                blog.setImage(imageBytes); // Set the image bytes to the blog entity
            }
            return blogRepository.save(blog);
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
            return null;

        return null;
    }
    */


    @Override
    public void deleteBuId(Long id) {
        blogRepository.deleteById(id);

    }
}