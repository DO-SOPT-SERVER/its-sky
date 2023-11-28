package org.sopt.Seminar.domain.post.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.sopt.Seminar.domain.post.dto.PostCreateRequest;
import org.sopt.Seminar.domain.post.service.PostServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v2/posts")
@RequiredArgsConstructor
public class PostControllerV2 {

    private static final String CUSTOM_AUTH_ID = "X-Auth-Id";
    private static final String POST_API_ENDPOINT = "/api/v2/posts";
    private final PostServiceV2 postService;

    @PostMapping
    public ResponseEntity<Void> createPostV2(@RequestHeader(CUSTOM_AUTH_ID) Long memberId,
                                             @RequestPart MultipartFile image,
                                             PostCreateRequest request) {
        URI location = URI.create(POST_API_ENDPOINT + postService.createV2(request, image, memberId));
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deleteByIdV2(postId);
        return ResponseEntity.noContent().build();
    }
}
