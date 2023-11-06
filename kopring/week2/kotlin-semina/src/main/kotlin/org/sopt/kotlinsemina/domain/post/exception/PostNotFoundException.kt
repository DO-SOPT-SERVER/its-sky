package org.sopt.kotlinsemina.domain.post.exception

import org.sopt.kotlinsemina.global.common.exception.ErrorType
import org.sopt.kotlinsemina.global.common.exception.SoptException

class PostNotFoundException: SoptException.NotFoundException(ErrorType.NotFound.POST_NOT_FOUND, "해당 게시물이 존재하지 않습니다.")