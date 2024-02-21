package com.codecraftery.Code.craftery.server.side.service;

import com.codecraftery.Code.craftery.server.side.dto.BlogDto;
import com.codecraftery.Code.craftery.server.side.model.Blog;

import java.io.IOException;
import java.util.List;

public interface BlogService {
    List<BlogDto> getAllBlogs() throws IOException;
    BlogDto findById(Long id);
    BlogDto addBlog(BlogDto blogDto) throws IOException;
    void deleteById(Long id);
}
