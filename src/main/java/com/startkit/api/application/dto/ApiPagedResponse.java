package com.startkit.api.application.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiPagedResponse<T> {
    private HttpStatus status;
    private String message;
    private List<T> data;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
