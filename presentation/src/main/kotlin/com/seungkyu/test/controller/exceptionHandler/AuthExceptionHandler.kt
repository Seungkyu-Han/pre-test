package com.seungkyu.test.controller.exceptionHandler

import com.seungkyu.test.application.author.exception.AuthorException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AuthExceptionHandler {

    @ExceptionHandler(AuthorException::class)
    fun authorExceptionHanding(authorException: AuthorException): ResponseEntity<String> =
        ResponseEntity
            .status(authorException.errorCode.httpStatus)
            .body(authorException.errorCode.message)
}