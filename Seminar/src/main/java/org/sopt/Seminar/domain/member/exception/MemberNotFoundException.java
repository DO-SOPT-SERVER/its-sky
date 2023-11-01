package org.sopt.Seminar.domain.member.exception;

import org.sopt.Seminar.global.common.exception.ErrorType.NotFound;
import org.sopt.Seminar.global.common.exception.SoptException;

public class MemberNotFoundException extends SoptException.NotFoundException {
    public MemberNotFoundException() {
        super(NotFound.USER_NOT_FOUND, "해당 멤버가 존재하지 않습니다.");
    }
}
