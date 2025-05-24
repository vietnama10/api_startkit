package com.startkit.api.application.dto;

import lombok.Data;

@Data
public class UserSearchParams {
    private String name;
    private String email;
    private int page = 1;
    private int size = 10;
    private Long totalElements;
    private Long totalPages;
}
