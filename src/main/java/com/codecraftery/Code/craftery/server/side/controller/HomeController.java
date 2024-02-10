package com.codecraftery.Code.craftery.server.side.controller;

import com.codecraftery.Code.craftery.server.side.model.Blog;
import com.codecraftery.Code.craftery.server.side.model.BlogCategory;
import com.codecraftery.Code.craftery.server.side.service.BlogCategoryService;
import com.codecraftery.Code.craftery.server.side.service.BlogService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class HomeController {
    private BlogCategoryService blogCategoryService;
    private BlogService blogService;

    public HomeController(BlogCategoryService blogCategoryService, BlogService blogService) {
        this.blogCategoryService = blogCategoryService;
        this.blogService = blogService;
    }
    @GetMapping("/data")
    public List<Blog> getAllBlogs(){
        List<Blog>blogs = blogService.getAllBlogs();
        System.out.println(blogs);
        return blogs;
    }
    @PostMapping("/create-blog")
    public ResponseEntity<Blog> uploadBlog(@RequestParam("image") MultipartFile imageFile,
                                           @RequestParam("title") String title,
                                           @RequestParam("text") String text,
                                           @RequestParam("category") String category) {
        try {
            // Retrieve the BlogCategory instance by name
            BlogCategory categoryObj = blogCategoryService.findByName(category);

            // Convert MultipartFile to File
            File image = convertMultipartFileToFile(imageFile);

            // Create the Blog object
            Blog blog = Blog.builder()
                    .title(title)
                    .text(text)
                    .blogCategory(categoryObj)
                    .build();

            // Save the Blog entity with the image
            Blog savedBlog = blogService.addBlog(blog, image);

            // Return the ResponseEntity with the saved Blog entity
            return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle exceptions and return Internal Server Error response
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Helper method to convert MultipartFile to File
    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        return file;
    }
}
