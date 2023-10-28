package org.sopt.Seminar.domain.post.controller;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.Seminar.domain.post.dto.PostCreateRequest;
import org.sopt.Seminar.domain.post.dto.PostGetResponse;
import org.sopt.Seminar.domain.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;
    private static final String CUSTOM_AUTH_ID = "X-Auth-Id";
    private static final String POST_API_ENDPOINT = "/api/post/";

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestHeader(CUSTOM_AUTH_ID) Long memberId,
                                           @RequestBody PostCreateRequest request) {
        URI location = URI.create(POST_API_ENDPOINT + postService.create(request, memberId));
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostGetResponse> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok().body(postService.getById(postId));
    }

    @GetMapping
    public ResponseEntity<List<PostGetResponse>> getPosts(@RequestHeader(CUSTOM_AUTH_ID) Long memberId) {
        return ResponseEntity.ok().body(postService.getPosts(memberId));
    }
}