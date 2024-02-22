package com.codecraftery.Code.craftery.server.side.service;

import com.codecraftery.Code.craftery.server.side.dto.BlogDto;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogServiceException;
import com.codecraftery.Code.craftery.server.side.model.Blog;

import java.io.IOException;
import java.util.List;

public interface BlogService {
    List<BlogDto> getAllBlogs() throws BlogServiceException;
    BlogDto findById(Long id) throws  BlogServiceException, BlogNotFoundException;
    BlogDto addBlog(BlogDto blogDto) throws BlogCreationException;
    void deleteById(Long id) throws  BlogServiceException;
    BlogDto updateBlog(BlogDto blogDto) throws BlogServiceException, BlogNotFoundException;
}
