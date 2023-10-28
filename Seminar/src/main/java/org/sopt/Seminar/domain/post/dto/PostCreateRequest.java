package org.sopt.Seminar.domain.post.dto;

public record PostCreateRequest(
        String title,
        String content
) {
}
