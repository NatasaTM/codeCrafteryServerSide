package com.codecraftery.Code.craftery.server.side.controller;

import com.codecraftery.Code.craftery.server.side.dto.BlogDto;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogServiceException;
import com.codecraftery.Code.craftery.server.side.service.BlogService;
import com.codecraftery.Code.craftery.server.side.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class BlogController {

    private final CategoryService categoryService;
    private final BlogService blogService;


    @GetMapping("/blogs")
    public ResponseEntity<List<BlogDto>> getAllBlogs() throws BlogServiceException {

        return ResponseEntity.ok(blogService.getAllBlogs());
    }


    @PostMapping("/create-blog")
    public ResponseEntity<BlogDto> createBlog(@RequestBody BlogDto blogDto) throws BlogCreationException {
        return new ResponseEntity<>(blogService.addBlog(blogDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-blog/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) throws BlogServiceException, BlogNotFoundException {

        blogService.deleteById(id);
        return ResponseEntity.ok().build();


    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<BlogDto> getBlog(@PathVariable Long id) throws BlogServiceException, BlogNotFoundException {

        BlogDto blog = blogService.findById(id);
        return ResponseEntity.ok(blog);


    }

    @PutMapping("/update-blog/{id}")
    public ResponseEntity<BlogDto> updateBlog(@PathVariable Long id, @RequestBody BlogDto blogDto) throws BlogServiceException, BlogNotFoundException, BlogCreationException {

        BlogDto updatedBlog = blogService.updateBlog(blogDto, id);
        return ResponseEntity.ok(updatedBlog);

    }


}
