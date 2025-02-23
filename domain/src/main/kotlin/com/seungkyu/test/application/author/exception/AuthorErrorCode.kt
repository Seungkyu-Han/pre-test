package com.seungkyu.test.application.author.exception

import org.springframework.http.HttpStatus

enum class AuthorErrorCode(
    val httpStatus: HttpStatus,
    val message: String
) {

    AUTHOR_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 작가를 찾을 수 없습니다."),
    AUTHOR_DUPLICATE_EMAIL(HttpStatus.CONFLICT, "중복되는 이메일이 존재합니다."),
    EXIST_RELATED_BOOK(HttpStatus.CONFLICT, "남아있는 도서 정보가 있기 때문에 삭제가 불가능합니다.")

}