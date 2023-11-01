package org.sopt.Seminar.domain.category.model;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Category {

    @EmbeddedId
    private CategoryId categoryId;

    private String content;
}
