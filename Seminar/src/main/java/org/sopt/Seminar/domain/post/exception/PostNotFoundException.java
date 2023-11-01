package org.sopt.Seminar.domain.post.exception;

import org.sopt.Seminar.global.common.exception.ErrorType.NotFound;
import org.sopt.Seminar.global.common.exception.SoptException;

public class PostNotFoundException extends SoptException.NotFoundException {
    public PostNotFoundException() {
        super(NotFound.POST_NOT_FOUND, "해당 게시물을 찾을 수 없습니다");
    }
}
