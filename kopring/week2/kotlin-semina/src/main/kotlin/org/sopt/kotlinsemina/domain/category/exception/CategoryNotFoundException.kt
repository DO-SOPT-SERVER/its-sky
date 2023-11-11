package org.sopt.kotlinsemina.domain.category.exception

import org.sopt.kotlinsemina.global.common.exception.ErrorType.NotFound.*
import org.sopt.kotlinsemina.global.common.exception.SoptException

class CategoryNotFoundException: SoptException.NotFoundException(CATEGORY_NOT_FOUND, "해당하는 카테고리가 없습니다.")