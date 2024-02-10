package com.codecraftery.Code.craftery.server.side.service;

import com.codecraftery.Code.craftery.server.side.dto.BlogDto;
import com.codecraftery.Code.craftery.server.side.model.Blog;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface BlogService {
    List<Blog> getAllBlogs() throws IOException;
    Blog findById(Long id);
    Blog addBlog(Blog blog) throws IOException;
    void deleteBuId(Long id);
}
