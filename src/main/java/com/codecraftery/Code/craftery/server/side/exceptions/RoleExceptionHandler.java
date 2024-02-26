package com.codecraftery.Code.craftery.server.side.exceptions;

import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryServiceException;
import com.codecraftery.Code.craftery.server.side.exceptions.roleExceptions.RoleCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.roleExceptions.RoleNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.roleExceptions.RoleServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * @author Natasa Todorov Markovic
 */
@ControllerAdvice
public class RoleExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CategoryExceptionHandler.class);

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoleNotFoundException(RoleNotFoundException ex) {

        logger.error("Role not found: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()));

    }

    @ExceptionHandler(RoleServiceException.class)
    public ResponseEntity<ErrorResponse> handleRoleServiceException(RoleServiceException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(RoleCreationException.class)
    public ResponseEntity<ErrorResponse> handleRoleCreationException(RoleCreationException ex) {
        logger.error("Failed to create role" + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()
        ));
    }
}


