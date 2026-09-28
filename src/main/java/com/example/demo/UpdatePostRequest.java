package com.example.demo;

import jakarta.validation.constraints.NotBlank;

public record UpdatePostRequest(
        @NotBlank(message = "제목 입력해주세요.")
        String title,
        @NotBlank(message = "제목 입력해주세요.")
        String content) {}

