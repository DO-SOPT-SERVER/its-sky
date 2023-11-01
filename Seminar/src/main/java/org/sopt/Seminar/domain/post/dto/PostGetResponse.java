package org.sopt.Seminar.domain.post.dto;

import org.sopt.Seminar.domain.category.model.Category;
import org.sopt.Seminar.domain.post.model.Post;

public record PostGetResponse(
        Long id,
        String title,
        String content,
        String category
) {
    public static PostGetResponse of(Post post, Category category) {
        return new PostGetResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                category.getContent()
        );
    }
}
