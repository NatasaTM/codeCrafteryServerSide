package com.codecraftery.Code.craftery.server.side.controller;

import com.codecraftery.Code.craftery.server.side.model.Blog;
import com.codecraftery.Code.craftery.server.side.model.BlogCategory;
import com.codecraftery.Code.craftery.server.side.service.BlogCategoryService;
import com.codecraftery.Code.craftery.server.side.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Blog> uploadBlog(@RequestParam("image") MultipartFile image,
                                           @RequestParam("title") String title,
                                           @RequestParam("text") String text,
                                           @RequestParam("category") String category) {
        try {
            BlogCategory _category = blogCategoryService.findByName(category); // You need to create an instance of BlogCategory here


            Blog blog = Blog.builder()
                    .title(title)
                    .text(text)
                    .blogCategory(_category)
                    .build();

            Blog savedBlog = blogService.addBlog(blog, image);

            return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
