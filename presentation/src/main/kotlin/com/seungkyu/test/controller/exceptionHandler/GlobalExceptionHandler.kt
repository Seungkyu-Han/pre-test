package com.seungkyu.test.controller.exceptionHandler

import com.seungkyu.test.controller.exceptionHandler.errorResponse.ErrorResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(methodArgumentNotValidException: MethodArgumentNotValidException): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorResponseDto(
                    message = methodArgumentNotValidException.bindingResult.fieldError?.defaultMessage ?:
                        "유효하지 않은 값이 입력되었습니다"
                )
            )
    }
}