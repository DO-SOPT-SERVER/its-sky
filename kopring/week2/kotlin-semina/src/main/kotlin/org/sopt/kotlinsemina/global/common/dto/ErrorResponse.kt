package org.sopt.kotlinsemina.global.common.dto

import org.sopt.kotlinsemina.global.common.exception.SoptException

data class ErrorResponse(
    val errorCode: Int,
    val errorType: String,
    val detail: String
) {
    constructor(soptException: SoptException): this(
        soptException.errorCode,
        soptException.errorType.toString(),
        soptException.detail
    )
}
