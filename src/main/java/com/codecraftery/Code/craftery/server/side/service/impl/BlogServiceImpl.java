package com.codecraftery.Code.craftery.server.side.service.impl;

import com.codecraftery.Code.craftery.server.side.model.Blog;
import com.codecraftery.Code.craftery.server.side.repository.BlogRepository;
import com.codecraftery.Code.craftery.server.side.service.BlogService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(Long id) {
        Blog blog = blogRepository.findById(id).get();
        return blog;
    }

    @Override
    public Blog addBlog(Blog blog, MultipartFile image) {

        try {
            if (image != null && !image.isEmpty()) {
                blog.setImage(image.getBytes()); // Set the image bytes to the blog entity
            }
            return blogRepository.save(blog);
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteBuId(Long id) {
        blogRepository.deleteById(id);

    }
}
