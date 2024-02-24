package com.codecraftery.Code.craftery.server.side.service.impl;

import com.codecraftery.Code.craftery.server.side.dto.BlogDto;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogServiceException;
import com.codecraftery.Code.craftery.server.side.model.Blog;
import com.codecraftery.Code.craftery.server.side.repository.BlogRepository;
import com.codecraftery.Code.craftery.server.side.service.BlogService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.codecraftery.Code.craftery.server.side.mapper.BlogMapper.mapBlogDtoToBlog;
import static com.codecraftery.Code.craftery.server.side.mapper.BlogMapper.mapBlogToBlogDto;


@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);
    private final Validator validator;


    @Override
    public List<BlogDto> getAllBlogs() throws BlogServiceException {
        try {
            List<BlogDto> blogs = blogRepository.findAll().stream()
                    .map(blog -> mapBlogToBlogDto(blog))
                    .collect(Collectors.toList());

            return blogs;
        } catch (DataAccessException ex) {
            logger.error("Error while retrieving blogs from the database", ex);
            throw new BlogServiceException("Failed to retrieve blogs", ex);
        }
    }

    @Override
    public BlogDto findById(Long id) throws BlogServiceException, BlogNotFoundException {
        try {
            Blog blog = blogRepository.findById(id)
                    .orElseThrow(() -> new BlogNotFoundException("Blog with ID " + id + " not found"));
            BlogDto blogDto = mapBlogToBlogDto(blog);
            return blogDto;
        } catch (DataAccessException e) {
            // Log the error if needed
            logger.error("Error finding blog with ID " + id, e);
            throw new BlogServiceException("Error finding blog with ID " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public BlogDto addBlog(BlogDto blogDto) throws BlogCreationException {

        Set<ConstraintViolation<Blog>> violations = getConstraintViolations(blogDto);
        if (!violations.isEmpty()) {
            logger.error("BlogValidation");
            throw new BlogCreationException(buildValidationErrorMessage(violations));

        }
        try {
            Blog blog = mapBlogDtoToBlog(blogDto);
            Blog savedBlog = blogRepository.save(blog);
            if (savedBlog == null) {
                throw new BlogCreationException("Error saving blog to database: ");
            }
            return mapBlogToBlogDto(savedBlog);
        } catch (DataAccessException e) {
            logger.error("Error occurred while adding blog to database" + e);
            throw new BlogCreationException("Error saving blog to database: " + e.getMessage(), e);
        }
    }

    private Set<ConstraintViolation<Blog>> getConstraintViolations(BlogDto blogDto) {
        Blog validateBlog = mapBlogDtoToBlog(blogDto);
        Set<ConstraintViolation<Blog>> violations = validator.validate(validateBlog); // Validate the Blog object
        return violations;
    }

    @Override
    public void deleteById(Long id) throws BlogServiceException, BlogNotFoundException {
        if (!blogRepository.existsById(id)) {
            throw new BlogNotFoundException("Blog with ID " + id + " not found");
        }
        try {
            blogRepository.deleteById(id);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new BlogServiceException("Error while deleting blog", ex);
        } catch (Exception e) {
            logger.error("Failed to delete blog" + e);
            throw new BlogServiceException("Failed to delete blog" + e.getMessage() + e);
        }

    }

    @Override
    public BlogDto updateBlog(BlogDto blogDto, Long id) throws BlogServiceException, BlogNotFoundException, BlogCreationException {

        Set<ConstraintViolation<Blog>> violations = getConstraintViolations(blogDto);
        if (!violations.isEmpty()) {
            logger.error("BlogValidation");
            throw new BlogCreationException(buildValidationErrorMessage(violations));
        }

        try {
            Blog blog = blogRepository.findById(id).get();
            if (blog == null) {
                throw new BlogNotFoundException("Blog with ID " + id + " not found");
            }

            blog.setTitle(blogDto.getTitle());
            blog.setText(blogDto.getText());
            blog.setImageUrl(blogDto.getImageUrl());
            blog.setBlogCategories(blogDto.getBlogCategories());

            return mapBlogToBlogDto(blogRepository.save(blog));
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new BlogServiceException("Error updating blog: " + e.getMessage(), e);
        }
    }

    private String buildValidationErrorMessage(Set<ConstraintViolation<Blog>> violations) {
        StringBuilder errorMessage = new StringBuilder("Validation errors:");
        for (ConstraintViolation<Blog> violation : violations) {
            errorMessage.append("<br>- ").append(violation.getPropertyPath()).append(": ").append(violation.getMessage());
        }
        return errorMessage.toString();
    }
}
