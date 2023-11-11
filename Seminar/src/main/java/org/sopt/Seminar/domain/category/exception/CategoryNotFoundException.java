package org.sopt.Seminar.domain.category.exception;

import org.sopt.Seminar.global.common.exception.ErrorType.NotFound;
import org.sopt.Seminar.global.common.exception.SoptException;

public class CategoryNotFoundException extends SoptException.NotFoundException {
    public CategoryNotFoundException() {
        super(NotFound.CATEGORY_NOT_FOUND, "해당하는 카테고리가 없습니다.");
    }
}
