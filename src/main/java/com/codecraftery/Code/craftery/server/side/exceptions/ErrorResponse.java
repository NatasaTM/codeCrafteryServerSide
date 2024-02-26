package com.codecraftery.Code.craftery.server.side.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Natasa Todorov Markovic
 */
@Data
@RequiredArgsConstructor(staticName = "of")
@Builder
public class ErrorResponse {
    private final int status;
    private final String message;
    private final LocalDateTime timestamp;
}
