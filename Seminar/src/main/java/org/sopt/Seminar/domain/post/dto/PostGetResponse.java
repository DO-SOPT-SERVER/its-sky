package org.sopt.Seminar.domain.post.dto;

import org.sopt.Seminar.domain.post.model.Post;

public record PostGetResponse(
        Long id,
        String title,
        String content
) {
    public static PostGetResponse of(Post post) {
        return new PostGetResponse(
                post.getId(),
                post.getTitle(),
                post.getContent()
        );
    }
}
