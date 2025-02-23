package com.seungkyu.test.application.book.dto

import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class CreateBookCommand(
    @field:NotNull(message = "이름을 입력해주세요")
    val title: String? = null,
    @field:NotNull(message = "설명을 입력해주세요")
    val description: String? = null,
    @field:NotNull(message = "isbn을 입력해주세요")
    val isbn: String? = null,
    @field:NotNull(message = "발행일을 입력해주세요")
    val publicationDate: LocalDate? = null,
    @field:NotNull(message = "작가의 id를 입력해주세요")
    val authorId: Int? = null,
)
