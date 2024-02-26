package com.codecraftery.Code.craftery.server.side.validation.impl;

import com.codecraftery.Code.craftery.server.side.model.Project;
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
public class ProjectValidator implements Validator<Project> {

    private final jakarta.validation.Validator validator;

    @Override
    public Set<ConstraintViolation<Project>> validate(Project project) {
        Set<ConstraintViolation<Project>> violations = validator.validate(project);
        return violations;
    }
}
