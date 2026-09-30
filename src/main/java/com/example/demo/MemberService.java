package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(
            MemberRepository memberRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;

    }
    @Transactional
    public Member signup(SignupRequest request) {
        if(memberRepository.existsByusername(request.username())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,"이미 사용중인 아이디 입니다."
            );
        }

        String hash = passwordEncoder.encode(request.password());

        Member member = new Member(request.username(), hash);

        return memberRepository.save(member);
    }
}
