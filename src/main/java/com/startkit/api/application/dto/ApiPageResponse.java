package com.startkit.api.application.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiPageResponse<T> extends ApiResponse<T> {
    private int page;
    private int size;
    private Long totalElements;
    private Long totalPages;

    public ApiPageResponse() {
        super();
    }

    public ApiPageResponse(HttpStatus status, String message, T data) {
        super(status, message, data);
    }
}
