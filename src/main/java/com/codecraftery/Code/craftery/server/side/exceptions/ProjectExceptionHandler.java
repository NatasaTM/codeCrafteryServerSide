package com.codecraftery.Code.craftery.server.side.exceptions;


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

/**
 * @author Natasa Todorov Markovic
 */
@ControllerAdvice
public class ProjectExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ProjectExceptionHandler.class);

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProjectNotFoundException(ProjectNotFoundException ex) {

        logger.error("Project not found: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()));

    }

    @ExceptionHandler(ProjectServiceException.class)
    public ResponseEntity<ErrorResponse> handleProjectServiceException(ProjectServiceException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(ProjectCreationException.class)
    public ResponseEntity<ErrorResponse> handleProjectCreationException(ProjectCreationException ex) {
        logger.error("Failed to create project" + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()
        ));
    }
}

