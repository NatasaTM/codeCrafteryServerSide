package com.codecraftery.Code.craftery.server.side.exceptions;

import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryServiceException;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions.ProjectServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CategoryExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CategoryExceptionHandler.class);

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException ex) {

        logger.error("Category not found: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()));

    }
    @ExceptionHandler(CategoryServiceException.class)
    public ResponseEntity<ErrorResponse> handleCategoryServiceException(CategoryServiceException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), LocalDateTime.now()));
    }
    @ExceptionHandler(CategoryCreationException.class)
    public ResponseEntity<ErrorResponse> handleCategoryCreationException (CategoryCreationException ex){
        logger.error("Failed to create category" + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()
        ));
    }
}
