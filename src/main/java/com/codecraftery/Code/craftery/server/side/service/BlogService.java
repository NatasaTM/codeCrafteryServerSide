package com.codecraftery.Code.craftery.server.side.service;

import com.codecraftery.Code.craftery.server.side.dto.BlogDto;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogServiceException;
import com.codecraftery.Code.craftery.server.side.exceptions.validationExcpetions.ValidationException;

import java.util.List;

/**
 * @author Natasa Todorov Markovic
 */
public interface BlogService {
    List<BlogDto> getAllBlogs() throws BlogServiceException;

    BlogDto findById(Long id) throws BlogServiceException, BlogNotFoundException;

    BlogDto addBlog(BlogDto blogDto) throws BlogCreationException, ValidationException;

    void deleteById(Long id) throws BlogServiceException, BlogNotFoundException;

    BlogDto updateBlog(BlogDto blogDto, Long id) throws BlogServiceException, BlogNotFoundException, ValidationException;
}
