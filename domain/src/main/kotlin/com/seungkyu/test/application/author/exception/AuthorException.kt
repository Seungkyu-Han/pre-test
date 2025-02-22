package com.seungkyu.test.application.author.exception

class AuthorException(
    val errorCode: AuthorErrorCode,
): RuntimeException(errorCode.message)