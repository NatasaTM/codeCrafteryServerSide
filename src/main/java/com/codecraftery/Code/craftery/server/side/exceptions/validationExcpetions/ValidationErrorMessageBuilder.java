package com.codecraftery.Code.craftery.server.side.exceptions.validationExcpetions;

import jakarta.validation.ConstraintViolation;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class ValidationErrorMessageBuilder <T>{

    public String buildValidationErrorMessage(Set<ConstraintViolation<T>> violations) {
        StringBuilder errorMessage = new StringBuilder("Validation errors:");
        for (ConstraintViolation<T> violation : violations) {
            errorMessage.append(" ").append(violation.getPropertyPath()).append(": ").append(violation.getMessage());
        }
        return errorMessage.toString();
    }

}
