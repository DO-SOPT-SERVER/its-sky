package org.sopt.kotlinsemina.global.common.exception

open class SoptException(
    errorType: ErrorType,
    detail: String
): RuntimeException() {
    val errorCode = errorType.errorCode
    val errorType = errorType
    val detail = detail

    abstract class InvalidRequestException(errorType: ErrorType.BadRequest, detail: String): SoptException(errorType, detail)

    abstract class NotFoundException(errorType: ErrorType.NotFound, detail: String): SoptException(errorType, detail)

    abstract class ServerErrorException(errorType: ErrorType.ServerError, detail: String): SoptException(errorType, detail)
}