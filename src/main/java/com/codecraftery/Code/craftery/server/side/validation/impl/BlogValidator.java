package com.codecraftery.Code.craftery.server.side.validation.impl;

import com.codecraftery.Code.craftery.server.side.model.Blog;
import com.codecraftery.Code.craftery.server.side.validation.Validator;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Natasa Todorov Markovic
 */
@Service
@RequiredArgsConstructor

public class BlogValidator implements Validator<Blog> {
    private final jakarta.validation.Validator validator;

    @Override
    public Set<ConstraintViolation<Blog>> validate(Blog blog) {
        Set<ConstraintViolation<Blog>> violations = validator.validate(blog);
        return violations;
    }
}
