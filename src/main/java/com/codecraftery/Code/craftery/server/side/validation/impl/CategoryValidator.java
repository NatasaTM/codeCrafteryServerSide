package com.codecraftery.Code.craftery.server.side.validation.impl;


import com.codecraftery.Code.craftery.server.side.model.Category;
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
public class CategoryValidator implements Validator<Category> {

    private final jakarta.validation.Validator validator;

    @Override
    public Set<ConstraintViolation<Category>> validate(Category category) {
        Set<ConstraintViolation<Category>> violations = validator.validate(category);
        return violations;
    }
}


