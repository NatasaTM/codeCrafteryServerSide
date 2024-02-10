package com.codecraftery.Code.craftery.server.side.mapper;

import com.codecraftery.Code.craftery.server.side.dto.BlogDto;
import com.codecraftery.Code.craftery.server.side.model.Blog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class BlogMapper {
    /*
    public static BlogDto mapBlogToBlogDto(Blog blog) throws IOException{
        File imageFile = convertByteArrayToFile(blog.getImageUrl(), "image.jpg");

        BlogDto blogDto = BlogDto.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .text(blog.getText())
                .image(imageFile)
                .createdOn(blog.getCreatedOn())
                .build();
        return blogDto;
    }
    private static File convertByteArrayToFile(byte[] byteArray, String fileName) throws IOException {
        File tempFile = File.createTempFile(fileName, null);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(byteArray);
        }
        return tempFile;
    }

    public static Blog matBlogDtoToBlog(BlogDto blogDto) throws IOException{
        byte[] imageBytes = convertFileToByteArray(blogDto.getImage());
        Blog blog = Blog.builder()
                .title(blogDto.getTitle())
                .text(blogDto.getText())
                .blogCategory(blogDto.getBlogCategory())
                .imageUrl(imageBytes)
                .build();
        return null;
    }
    private static byte[] convertFileToByteArray(File image) throws IOException {
        return Files.readAllBytes(image.toPath());
    }

     */
}
