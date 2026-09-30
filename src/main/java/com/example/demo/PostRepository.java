package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepository
        extends JpaRepository<Post,Long> {
        Page<Post> findByTitleContainingAndIdGreaterThanEqual(String keywords ,Long minId, Pageable pageable );
        Page<Post> findByIdGreaterThanEqual(Long minId, Pageable pageable);
        Page<Post> findByTitleContaining(String keywords, Pageable pageable);

}
