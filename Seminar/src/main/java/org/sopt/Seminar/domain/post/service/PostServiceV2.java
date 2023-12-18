package org.sopt.Seminar.domain.post.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.sopt.Seminar.domain.member.model.Member;
import org.sopt.Seminar.domain.member.repository.MemberRepository;
import org.sopt.Seminar.domain.post.dto.PostCreateRequest;
import org.sopt.Seminar.domain.post.exception.PostNotFoundException;
import org.sopt.Seminar.domain.post.model.Post;
import org.sopt.Seminar.domain.post.repository.PostRepository;
import org.sopt.Seminar.global.common.service.S3Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceV2 {

    private static final String POST_IMAGE_FOLDER_NAME = "posts/";

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final S3Service s3Service;

    @Transactional
    public String createV2(PostCreateRequest request, MultipartFile image, Long memberId) {
        try {
            final String imageUrl = s3Service.uploadImage(POST_IMAGE_FOLDER_NAME, image);
            Member member = memberRepository.findByIdOrThrow(memberId);
            Post post = postRepository.save(
                    Post.builderWithImageUrl()
                            .title(request.title())
                            .content(request.content())
                            .imageUrl(imageUrl)
                            .member(member)
                            .build());
            return post.getId().toString();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void deleteByIdV2(Long postId) {
        try {
            Post post = postRepository.findById(postId)
                    .orElseThrow(PostNotFoundException::new);
            s3Service.deleteImage(post.getImageUrl());
            postRepository.deleteById(postId);
        } catch (IOException | RuntimeException e) {
            throw  new RuntimeException(e.getMessage());
        }
    }
}
