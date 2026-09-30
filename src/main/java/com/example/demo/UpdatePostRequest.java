package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePostRequest(
        @NotBlank(message = "제목 입력해주세요.")
        @Size(max = 30 , message ="게시글 제목은 30자이하여야 합니다.")
        String title,
        @NotBlank(message = "제목 입력해주세요.")
        String content) {}

