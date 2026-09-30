package com.example.demo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(
        @NotBlank(message = "제목을 입력해주세요.")
        @Size(max = 30 , message = "게시글 제목은 30자 이하여야합니다.")
        String title,

        @NotBlank(message = "제목을 입력해주세요.")
        String content) {}

