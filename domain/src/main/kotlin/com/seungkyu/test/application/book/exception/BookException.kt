package com.seungkyu.test.application.book.exception

class BookException(
    val errorCode: BookErrorCode,
): RuntimeException(errorCode.message)