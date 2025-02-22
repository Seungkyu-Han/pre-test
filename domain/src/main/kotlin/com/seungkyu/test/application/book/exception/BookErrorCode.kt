package com.seungkyu.test.application.book.exception

import org.springframework.http.HttpStatus

enum class BookErrorCode(
    val httpStatus: HttpStatus,
    val message: String
) {

    INVALID_ISBN(HttpStatus.BAD_REQUEST, "유효하지 않은 ISBN입니다."),
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 책을 찾을 수 없습니다."),

}