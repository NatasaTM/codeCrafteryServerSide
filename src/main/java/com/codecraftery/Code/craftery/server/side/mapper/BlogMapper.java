package com.codecraftery.Code.craftery.server.side.mapper;

import com.codecraftery.Code.craftery.server.side.dto.BlogDto;
import com.codecraftery.Code.craftery.server.side.model.Blog;

/**
 * @author Natasa Todorov Markovic
 */
public class BlogMapper {

    public static BlogDto mapBlogToBlogDto(Blog blog) {


        BlogDto blogDto = BlogDto.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .text(blog.getText())
                .imageUrl(blog.getImageUrl())
                .createdOn(blog.getCreatedOn())
                .blogCategories(blog.getBlogCategories())
                .build();
        return blogDto;
    }

    public static Blog mapBlogDtoToBlog(BlogDto blogDto) {

        Blog blog = Blog.builder()
                .title(blogDto.getTitle())
                .text(blogDto.getText())
                .blogCategories(blogDto.getBlogCategories())
                .imageUrl(blogDto.getImageUrl())
                .build();
        return blog;
    }


}
