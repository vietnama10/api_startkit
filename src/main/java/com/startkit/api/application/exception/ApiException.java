package com.startkit.api.application.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException {
    private HttpStatus status;
    private final String message;
    private final LocalDateTime timestamp;
}
