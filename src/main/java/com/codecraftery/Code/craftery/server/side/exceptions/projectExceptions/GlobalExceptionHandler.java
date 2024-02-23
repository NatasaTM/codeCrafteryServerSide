package com.codecraftery.Code.craftery.server.side.exceptions.projectExceptions;

import com.codecraftery.Code.craftery.server.side.exceptions.BlogExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(BlogExceptionHandler.class);
}
