package org.sopt.Seminar.domain.member.exception;

import org.sopt.Seminar.global.common.exception.ErrorType.NotFound;
import org.sopt.Seminar.global.common.exception.SoptException;

public class UserNotFoundException extends SoptException.NotFoundException {
    public UserNotFoundException() {
        super(NotFound.USER_NOT_FOUND, "해당 유저가 존재하지 않습니다.");
    }
}
