package org.sopt.Seminar.domain.category.model;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = PROTECTED)
public class CategoryId implements Serializable {
    private Short id;

    public CategoryId(Short id) {
        this.id = id;
    }
}
