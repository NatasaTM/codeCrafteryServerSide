package com.codecraftery.Code.craftery.server.side.service;

import com.codecraftery.Code.craftery.server.side.model.Blog;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogService {
    List<Blog> getAllBlogs();
    Blog findById(Long id);
    Blog addBlog(Blog blog, MultipartFile image);
    void deleteBuId(Long id);
}
