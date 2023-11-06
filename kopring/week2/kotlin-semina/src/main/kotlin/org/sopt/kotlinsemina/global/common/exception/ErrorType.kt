package org.sopt.kotlinsemina.global.common.exception

interface ErrorType {
    val errorCode: Int

    enum class BadRequest(override val errorCode: Int): ErrorType {
        ;
    }

    enum class NotFound(override val errorCode: Int): ErrorType {
        POST_NOT_FOUND(4001),
        MEMBER_NOT_FOUND(4002),
        CATEGORY_NOT_FOUND(4003)
    }

    enum class ServerError(override val errorCode: Int): ErrorType {
        SERVER_ERROR_DEFAULT(5000)
    }
}