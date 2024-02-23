package com.codecraftery.Code.craftery.server.side.exceptions;

import lombok.*;

import java.time.LocalDateTime;
@Data
@RequiredArgsConstructor(staticName = "of")
@Builder
public class ErrorResponse {
    private final int status;
    private final String message;
    private final LocalDateTime timestamp;
}
