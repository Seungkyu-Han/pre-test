package com.seungkyu.test.application.book.dto

import java.time.LocalDate

data class BookInfoResponse(
    val id: Int,
    val title: String,
    val description: String,
    val isbn: String,
    val publicationDate: LocalDate,
    val authorId: Int,
)