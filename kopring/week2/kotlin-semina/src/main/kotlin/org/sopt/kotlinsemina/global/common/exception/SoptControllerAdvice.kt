package org.sopt.kotlinsemina.global.common.exception

import org.sopt.kotlinsemina.global.common.dto.ErrorResponse
import org.sopt.kotlinsemina.global.common.exception.SoptException.*
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class SoptControllerAdvice {
    @ExceptionHandler(InvalidRequestException::class)
    fun handleInvalidRequestException(ex: InvalidRequestException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(BAD_REQUEST).body(ErrorResponse(ex))
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(NOT_FOUND).body(ErrorResponse(ex))
    }

    @ExceptionHandler(ServerErrorException::class)
    fun handleServerErrorException(ex: ServerErrorException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ErrorResponse(ex))
    }
}