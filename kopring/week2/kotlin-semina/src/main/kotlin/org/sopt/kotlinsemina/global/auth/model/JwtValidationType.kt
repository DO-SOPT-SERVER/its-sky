package org.sopt.kotlinsemina.global.auth.model

enum class JwtValidationType {
    VALID_JWT,
    INVALID_JWT_TOKEN,
    EXPIRED_JWT_TOKEN,
    UNSUPPORTED_JWT_TOKEN,
    EMPTY_JWT
}