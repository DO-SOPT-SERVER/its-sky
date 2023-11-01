package org.sopt.Seminar.domain.category.dto;

import org.sopt.Seminar.domain.category.model.Category;

public record CategoryResponse(
        String content
) {
    public static CategoryResponse of(Category category) {
        return new CategoryResponse(category.getContent());
    }
}
