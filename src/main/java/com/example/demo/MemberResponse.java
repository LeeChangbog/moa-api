package com.example.demo;

public record MemberResponse(Long id , String username) {
    public static MemberResponse from(Member member){
        return new MemberResponse(
                member.getid(),
                member.getUsername()
        );
    }
}
