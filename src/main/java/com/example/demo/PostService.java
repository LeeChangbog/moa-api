package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import java.util.List;



import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class PostService {
    //postRepository의 생성자 주입 섹션,
    //postservice안에서 postRepository를 사용하기 위함임
    //이걸 안하고 그냥 postRepository를 사용하면 에러가 뜸
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }



    public Page<Post> getPosts(String keyword,Pageable pageable,Long minId) {
        // 서버 실행시킨 순간 메서들도 로딩이 됨 다만 서버가 부를때 까지 기다림.

        if((keyword == null || keyword.isBlank()) && (minId == null)){
            return postRepository.findAll(pageable);
        }
        else if((keyword != null && !keyword.isBlank()) && (minId == null)){
            return postRepository.findByTitleContaining(keyword , pageable);

        }
        else if((keyword == null || keyword.isBlank()) && (minId != null)){
            return postRepository.findByIdGreaterThanEqual(minId,pageable);
        }
        return postRepository.findByTitleContainingAndIdGreaterThanEqual(keyword,minId,pageable);
    }



    //getpost(1~N)이런 식으로 호출 되면 postReposiory로 가 조건식을 검사함 있다면 그에 맞는
    //객체를 반환
    //optional<Post>안에는 post가 있을지 없을지 모름, 함수를 실행하고 있으면 집어넣음
    //함수를 람다식으로 사용해서 어려울 수 있지만 풀어서 설명함,
    //Optional<Post> result = postRepository.findById(3L);
    //Post post = result.orElseThrow(
    //        () -> new ResponseStatusException(
    //                HttpStatus.NOT_FOUND, "게시글 없습니다."
    //        )
    //);
    //이런식으로 작동함
    public Post getPost(Long id) {

       return postRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"게시글 없습니다."
       ));
    }

    //새로운 객체를 만듦, Request json을 받고 거기서.title,.content로 추출,
    //후 postRepository로 객체 sql에 저장
    @Transactional
    public Post createPost(CreatePostRequest request) {
        Post post = new Post(request.title(),request.content());
        return postRepository.save(post);
    }

    //update할 id 인자값을 받음 새로운 post객체에 기존 post(i)로 받은 객체를 넘김
    //post.update로 객체 값을, 업데이트함
    //그 후 post 반환
    @Transactional
    public Post updatePost(Long id , UpdatePostRequest request){
            Post post = getPost(id);
            post.update(request.title(),request.content());
            return post;

    }

    //deleat할 id를 인자값으로 받음 getpost로 객체 를 받음
    @Transactional
    public void deletePost(Long id) {
        Post post = getPost(id);
        postRepository.delete(post);
    }


    }
