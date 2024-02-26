package com.codecraftery.Code.craftery.server.side.validation.impl;

import com.codecraftery.Code.craftery.server.side.model.Role;
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
public class RoleValidator implements Validator<Role> {
    private final jakarta.validation.Validator validator;

    @Override
    public Set<ConstraintViolation<Role>> validate(Role role) {
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        return violations;
    }
}


