package com.seungkyu.test.domain.book.exception

class BookDomainException(
    override val message: String
): RuntimeException(message)