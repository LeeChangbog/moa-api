package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignupRequest(
    @NotBlank(message = "아이디 입력 요망")
    @Pattern(
            regexp = "[a-zA-Z0-9_]{4,50}",
            message = "아이디는 영문 대소문자, 숫자,밑줄로 4~50자이여야 합니다. "

    )
    String username,
    @NotBlank(message = "비밀번호 입력 요망")
    @Size(min = 8, max = 64 , message = "비밀번호는 8~64자 여야 합니다.")
    @Pattern(
            regexp = "[\\x21-\\x7E]+",
            message = "비밀번호는 공백없음, 영문,숫자,특수문자를 사용해주세요"
    )
    String password
) {}
