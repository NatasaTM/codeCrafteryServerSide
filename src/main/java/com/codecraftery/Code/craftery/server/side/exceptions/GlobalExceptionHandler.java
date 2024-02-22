package com.codecraftery.Code.craftery.server.side.exceptions;

import com.codecraftery.Code.craftery.server.side.exceptions.blogExceptions.BlogNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<Object> handleBlogNotFoundException(BlogNotFoundException ex) {
        // Create a custom error response
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", ex.getMessage());
        responseBody.put("timestamp", LocalDateTime.now());
        responseBody.put("status", HttpStatus.NOT_FOUND.value());

        // Return the custom response with NOT_FOUND status
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
