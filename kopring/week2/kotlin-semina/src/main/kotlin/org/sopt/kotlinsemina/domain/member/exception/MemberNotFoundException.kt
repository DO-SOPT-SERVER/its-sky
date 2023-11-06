package org.sopt.kotlinsemina.domain.member.exception

import org.sopt.kotlinsemina.global.common.exception.ErrorType
import org.sopt.kotlinsemina.global.common.exception.SoptException

class MemberNotFoundException: SoptException.NotFoundException(ErrorType.NotFound.MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다.")