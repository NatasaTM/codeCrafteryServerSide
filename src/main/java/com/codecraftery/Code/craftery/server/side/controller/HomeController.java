package com.codecraftery.Code.craftery.server.side.controller;

import com.codecraftery.Code.craftery.server.side.dto.BlogDto;
import com.codecraftery.Code.craftery.server.side.model.Blog;
import com.codecraftery.Code.craftery.server.side.model.BlogCategory;
import com.codecraftery.Code.craftery.server.side.service.BlogCategoryService;
import com.codecraftery.Code.craftery.server.side.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        List<Blog>blogs = null;
        try {
            blogs = blogService.getAllBlogs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return blogs;
    }

/*
    @PostMapping("/create-blog")
    public ResponseEntity<Blog> uploadBlog(@RequestParam("image") MultipartFile imageFile,
                                              @RequestParam("title") String title,
                                              @RequestParam("text") String text,
                                              @RequestParam("category") String category) {
        try {
            // Retrieve the BlogCategory instance by name
            BlogCategory categoryObj = blogCategoryService.findByName(category);
             File image = convertMultipartFileToFile(imageFile);
            // Create the BlogDto object
            Blog blog = Blog.builder()
                    .title(title)
                    .text(text)
                    .blogCategory(categoryObj)
                    .image(image)
                    .build();

            // Save the BlogDto entity
            Blog savedBlog = blogService.addBlog(blog);

            // Return the ResponseEntity with the saved BlogDto entity
            return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle exceptions and return Internal Server Error response
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Helper method to convert MultipartFile to File
    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        String uploadDir = "C:\\Users\\Win10\\IdeaProjects\\Code-craftery-server-side\\src\\main\\resources\\static";
        File file = new File(uploadDir + File.separator + multipartFile.getOriginalFilename());
        FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        return file;
    }



    @PostMapping("/create-blog")
    public ResponseEntity<Blog> uploadBlog(@RequestParam("image") MultipartFile imageFile,
                                           @RequestParam("title") String title,
                                           @RequestParam("text") String text,
                                           @RequestParam("category") String category) {
        try {
            // Retrieve the BlogCategory instance by name
            BlogCategory categoryObj = blogCategoryService.findByName(category);

            // Convert MultipartFile to byte[]
            byte[] imageBytes = convertMultipartFileToBytes(imageFile);

            // Create the Blog object
            Blog blog = Blog.builder()
                    .title(title)
                    .text(text)
                    .blogCategory(categoryObj)
                    .image(imageBytes) // Assign byte[] to image field
                    .build();

            // Save the Blog entity
            Blog savedBlog = blogService.addBlog(blog);

            // Return the ResponseEntity with the saved Blog entity
            return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle exceptions and return Internal Server Error response
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Helper method to convert MultipartFile to byte[]
    private byte[] convertMultipartFileToBytes(MultipartFile multipartFile) throws IOException {
        return multipartFile.getBytes();
    }
*/
@PostMapping("/create-blog")
public ResponseEntity<Blog> uploadBlog(@RequestParam("image") String imageUrl,
                                       @RequestParam("title") String title,
                                       @RequestParam("text") String text,
                                       @RequestParam("category") String category) {
    try {
        // Retrieve the BlogCategory instance by name
        BlogCategory categoryObj = blogCategoryService.findByName(category);

        // Save the image file and get the path
      //  String imagePath = saveImage(imageFile);

        // Create the Blog object
        Blog blog = Blog.builder()
                .title(title)
                .text(text)
                .blogCategory(categoryObj)
                .imageUrl(imageUrl) // Assign imagePath to image field
                .build();

        // Save the Blog entity
        Blog savedBlog = blogService.addBlog(blog);

        // Return the ResponseEntity with the saved Blog entity
        return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
    } catch (Exception e) {
        // Handle exceptions and return Internal Server Error response
        e.printStackTrace();
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    // Helper method to save image file and return its path
    private String saveImage(MultipartFile imageFile) throws IOException {
        String fileName = imageFile.getOriginalFilename();
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8); // Encode the filename

        // Save the image file to the static directory
        Path uploadDir = Paths.get("static");
        Path filePath = uploadDir.resolve(encodedFileName);
        Files.createDirectories(uploadDir); // Ensure the directory exists
        imageFile.transferTo(filePath); // Save the image file

        // Return the relative path to the saved image
        return "/static/" + encodedFileName;
    }

}
