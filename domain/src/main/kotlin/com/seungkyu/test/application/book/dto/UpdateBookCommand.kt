package com.seungkyu.test.application.book.dto

import java.time.LocalDate

data class UpdateBookCommand(
    val title: String,
    val description: String,
    val isbn: String,
    val publicationDate: LocalDate,
    val authorId: Int,
)