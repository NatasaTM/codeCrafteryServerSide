package com.codecraftery.Code.craftery.server.side.controller;

import com.codecraftery.Code.craftery.server.side.dto.BlogDto;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogServiceException;
import com.codecraftery.Code.craftery.server.side.model.Category;
import com.codecraftery.Code.craftery.server.side.service.BlogService;
import com.codecraftery.Code.craftery.server.side.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BlogController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogService blogService;


    public BlogController(CategoryService blogCategoryService, BlogService blogService) {
        this.categoryService = blogCategoryService;
        this.blogService = blogService;
    }

    @GetMapping("/blogs")
    public ResponseEntity<List<BlogDto>> getAllBlogs() {
        List<BlogDto> blogs = null;
        try {
            blogs = blogService.getAllBlogs();
            return ResponseEntity.ok(blogs);
        } catch (BlogServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @PostMapping("/create-blog")
    public ResponseEntity<BlogDto> saveBlog(@RequestParam("image") String imageUrl,
                                            @RequestParam("title") String title,
                                            @RequestParam("text") String text,
                                            @RequestParam("categories") List<Long> categoryIds) {
        try {

            List<Category> categories = categoryService.findListById(categoryIds);


            // Create the Blog object
            BlogDto blog = BlogDto.builder()
                    .title(title)
                    .text(text)
                    .blogCategories(categories)
                    .imageUrl(imageUrl) // Assign imagePath to image field
                    .build();


            // Save the Blog entity
            BlogDto savedBlog = blogService.addBlog(blog);

            // Return the ResponseEntity with the saved Blog entity
            return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
        } catch (BlogCreationException e) {
            // Handle exceptions and return Internal Server Error response
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-blog/{id}")
    public ResponseEntity deleteBlog(@PathVariable Long id) {
        try {
            blogService.deleteById(id);
            return ResponseEntity.ok().body("Blog with ID " + id + " deleted successfully");
        } catch (BlogServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/blogs/{id}")
    public ResponseEntity<?> getBlog(@PathVariable Long id) throws BlogServiceException, BlogNotFoundException {

         BlogDto blog = blogService.findById(id);
            if (blog == null) {
                throw new BlogNotFoundException("Blog with ID " + id + " not found");
            }
          return ResponseEntity.ok(blog);


    }


}
