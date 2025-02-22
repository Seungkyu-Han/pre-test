package com.seungkyu.test.application.book.dto

data class BookInfoResponses(
    val books: List<BookInfoResponse>,
    val totalElement: Long
)
