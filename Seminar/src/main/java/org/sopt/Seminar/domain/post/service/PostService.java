package org.sopt.Seminar.domain.post.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.sopt.Seminar.domain.member.model.Member;
import org.sopt.Seminar.domain.member.repository.MemberRepository;
import org.sopt.Seminar.domain.post.dto.PostCreateRequest;
import org.sopt.Seminar.domain.post.dto.PostGetResponse;
import org.sopt.Seminar.domain.post.dto.PostUpdateRequest;
import org.sopt.Seminar.domain.post.model.Post;
import org.sopt.Seminar.domain.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String create(PostCreateRequest request, Long memberId) {
        Member member = memberRepository.findByIdOrThrow(memberId);
        Post post = postRepository.save(
                Post.builder()
                .title(request.title())
                .content(request.content())
                .member(member)
                .build());

        return post.getId().toString();
    }

    public PostGetResponse getById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        return PostGetResponse.of(post);
    }

    public List<PostGetResponse> getPosts(Long memberId) {
        Member member = memberRepository.findByIdOrThrow(memberId);
        List<Post> posts = postRepository.findAllByMember(member);

        return posts.stream().map(PostGetResponse::of).collect(Collectors.toList());
    }

    @Transactional
    public void editContent(Long postId, PostUpdateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시물을 찾을 수 없습니다."));
        post.updateContent(request.content());
    }

    @Transactional
    public void deleteById(Long postId) {
        postRepository.deleteById(postId);
    }
}
