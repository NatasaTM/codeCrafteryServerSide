package com.codecraftery.Code.craftery.server.side.controller;

import com.codecraftery.Code.craftery.server.side.dto.BlogDto;
import com.codecraftery.Code.craftery.server.side.model.Blog;
import com.codecraftery.Code.craftery.server.side.model.Category;
import com.codecraftery.Code.craftery.server.side.service.CategoryService;
import com.codecraftery.Code.craftery.server.side.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class HomeController {
    private CategoryService categoryService;
    private BlogService blogService;

    public HomeController(CategoryService blogCategoryService, BlogService blogService) {
        this.categoryService = blogCategoryService;
        this.blogService = blogService;
    }
    @GetMapping("/data")
    public List<BlogDto> getAllBlogs(){
        List<BlogDto>blogs = null;
        try {
            blogs = blogService.getAllBlogs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return blogs;
    }


@PostMapping("/create-blog")
public ResponseEntity<BlogDto> uploadBlog(@RequestParam("image") String imageUrl,
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
    } catch (Exception e) {
        // Handle exceptions and return Internal Server Error response
        e.printStackTrace();
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}



}
