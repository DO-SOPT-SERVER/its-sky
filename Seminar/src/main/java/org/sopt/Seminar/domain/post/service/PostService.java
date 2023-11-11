package org.sopt.Seminar.domain.post.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.Seminar.domain.category.model.Category;
import org.sopt.Seminar.domain.category.model.CategoryId;
import org.sopt.Seminar.domain.category.service.CategoryService;
import org.sopt.Seminar.domain.member.model.Member;
import org.sopt.Seminar.domain.member.repository.MemberRepository;
import org.sopt.Seminar.domain.post.dto.PostCreateRequest;
import org.sopt.Seminar.domain.post.dto.PostGetResponse;
import org.sopt.Seminar.domain.post.dto.PostUpdateRequest;
import org.sopt.Seminar.domain.post.exception.PostNotFoundException;
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
    private final CategoryService categoryService;

    @Transactional
    public String create(PostCreateRequest request, Long memberId) {
        Member member = memberRepository.findByIdOrThrow(memberId);
        CategoryId categoryId = new CategoryId(request.categoryId());
        Post post = postRepository.save(
                Post.builder()
                .title(request.title())
                .content(request.content())
                .member(member)
                .categoryId(categoryId)
                .build());

        return post.getId().toString();
    }

    public PostGetResponse getById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        return PostGetResponse.of(post, getCategoryByPost(post));
    }

    public List<PostGetResponse> getPosts(Long memberId) {
        Member member = memberRepository.findByIdOrThrow(memberId);
        List<Post> posts = postRepository.findAllByMember(member);

        return posts.stream().map(post -> PostGetResponse.of(post, getCategoryByPost(post))).toList();
    }

    @Transactional
    public void editContent(Long postId, PostUpdateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        post.updateContent(request.content());
    }

    @Transactional
    public void deleteById(Long postId) {
        postRepository.deleteById(postId);
    }

    private Category getCategoryByPost(Post post) {
        return categoryService.getByCategoryId(post.getCategoryId());
    }
}
