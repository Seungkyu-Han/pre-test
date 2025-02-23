package com.seungkyu.test.controller.exceptionHandler

import com.seungkyu.test.application.book.exception.BookException
import com.seungkyu.test.controller.exceptionHandler.errorResponse.ErrorResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BookExceptionHandler {

    @ExceptionHandler(BookException::class)
    fun bookExceptionHandling(bookException: BookException): ResponseEntity<ErrorResponseDto> =
        ResponseEntity
            .status(bookException.errorCode.httpStatus)
            .body(ErrorResponseDto(message = bookException.errorCode.message))
}