package com.codecraftery.Code.craftery.server.side.service.impl;

import com.codecraftery.Code.craftery.server.side.dto.BlogDto;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogServiceException;
import com.codecraftery.Code.craftery.server.side.model.Blog;
import com.codecraftery.Code.craftery.server.side.repository.BlogRepository;
import com.codecraftery.Code.craftery.server.side.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codecraftery.Code.craftery.server.side.mapper.BlogMapper.mapBlogDtoToBlog;
import static com.codecraftery.Code.craftery.server.side.mapper.BlogMapper.mapBlogToBlogDto;


@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;


    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<BlogDto> getAllBlogs() throws BlogServiceException {
        try {
            return blogRepository.findAll().stream()
                    .map(blog -> mapBlogToBlogDto(blog))
                    .collect(Collectors.toList());
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new BlogServiceException("Error while retrieving blogs from the database", ex);
        }catch(Exception e){
            e.printStackTrace();
            throw  new BlogServiceException("Failed to retrieve blogs from the database"+ e.getMessage()+e);
        }
    }
    @Override
    public BlogDto findById(Long id) throws BlogServiceException, BlogNotFoundException {
        Blog blog = blogRepository.findById(id).get();
        BlogDto blogDto = mapBlogToBlogDto(blog);
        return blogDto;
    }

    @Override
    public BlogDto addBlog(BlogDto blogDto) throws BlogCreationException {
        try {
            Blog blog = mapBlogDtoToBlog(blogDto);
            Blog savedBlog = blogRepository.save(blog);
            return mapBlogToBlogDto(savedBlog);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new BlogCreationException("Error saving blog to database: " + e.getMessage(), e);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BlogCreationException("Failed to create blog: " + ex.getMessage(), ex);
        }
    }


    @Override
    public void deleteById(Long id) throws BlogServiceException {
        try {
            blogRepository.deleteById(id);
        }  catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new BlogServiceException("Error while deleting blog", ex);
        }catch(Exception e){
            e.printStackTrace();
            throw  new BlogServiceException("Failed to delete blog"+ e.getMessage()+e);
        }

    }

    @Override
    public BlogDto updateBlog(BlogDto blogDto) throws BlogServiceException, BlogNotFoundException {
        try {
            Long id = blogDto.getId();
            Blog existingBlog = blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException("Blog with ID " + id + " not found"));
            existingBlog.setTitle(blogDto.getTitle()); // Update other fields as needed
            Blog savedBlog = blogRepository.save(existingBlog);
            return mapBlogToBlogDto(savedBlog);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new BlogServiceException("Error updating blog: " + e.getMessage(), e);
        }
    }
}
