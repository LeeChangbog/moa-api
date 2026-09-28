package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import java.util.List;


@RestController // 응답 처리하고 메서드 반환값을 응답 본문으로 보냄.
public class PostController { // 이 아래부터 요청을 처리할 메서드들. 클래스로 표시

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public PostPageResponse getposts(@RequestParam(required = false,name = "keyword") String keyword,
                               @PageableDefault( size = 10,
                                                 sort = "id",
                                                 direction = Sort.Direction.DESC
                               )Pageable pageable)
    {
        Page<Post> posts = postService.getPosts(keyword,pageable);
        Page<PostResponse> responses = posts.map(PostResponse::from);

        return new PostPageResponse(
                responses.getContent(),
                responses.getNumber(),
                responses.getSize(),
                responses.getTotalElements(),
                responses.getTotalPages()
        );
    }
    @GetMapping("/posts/{id}")
    public PostResponse getPost(@PathVariable("id")Long id) {
        Post post = postService.getPost(id);
        return PostResponse.from(post);
    }

    @PostMapping ("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@Valid @RequestBody CreatePostRequest request) {
        Post post = postService.createPost(request);
        return PostResponse.from(post);
    }

    @PutMapping("/posts/{id}")
    public PostResponse updatePost(@PathVariable("id")Long id ,
                           @Valid @RequestBody UpdatePostRequest request) {
        Post post = postService.updatePost(id,request);
        return PostResponse.from(post);
    }

    @DeleteMapping("posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id")Long id){
        postService.deletePost(id);

    }





}
