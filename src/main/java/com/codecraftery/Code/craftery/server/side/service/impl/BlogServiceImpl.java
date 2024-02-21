package com.codecraftery.Code.craftery.server.side.service.impl;

import com.codecraftery.Code.craftery.server.side.dto.BlogDto;
import com.codecraftery.Code.craftery.server.side.model.Blog;
import com.codecraftery.Code.craftery.server.side.repository.BlogRepository;
import com.codecraftery.Code.craftery.server.side.service.BlogService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codecraftery.Code.craftery.server.side.mapper.BlogMapper.mapBlogDtoToBlog;
import static com.codecraftery.Code.craftery.server.side.mapper.BlogMapper.mapBlogToBlogDto;


@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<BlogDto> getAllBlogs()  {
        List<Blog> blogs = blogRepository.findAll();
        List<BlogDto> blogDtos = new ArrayList<>();
        for (int i = 0; i <blogs.size() ; i++) {
            BlogDto blogDto = mapBlogToBlogDto(blogs.get(i));
            blogDtos.add(blogDto);
        }


        return   blogDtos;
    }

    @Override
    public BlogDto findById(Long id) {
        Blog blog = blogRepository.findById(id).get();
        BlogDto blogDto = mapBlogToBlogDto(blog);
        return blogDto;
    }

    @Override
    public BlogDto addBlog(BlogDto blogDto) throws IOException {
       Blog blog = blogRepository.save(mapBlogDtoToBlog(blogDto));
        return mapBlogToBlogDto(blog);
    }


    @Override
    public void deleteById(Long id) {
        blogRepository.deleteById(id);

    }
}
