package com.seungkyu.test.application.book.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(name = "도서 생성 응답")
data class CreateBookResponse(

    @field:Schema(description = "등록된 도서의 이름", example = "도서 이름", required = true)
    val title: String,

    @field:Schema(description = "등록된 도서의 설명", example = "도서 설명", required = true)
    val description: String,

    @field:Schema(description = "등록된 도서의 isbn", example = "1000000000", required = true)
    val isbn: String,

    @field:Schema(description = "등록된 도서의 출판일", example = "2025-02-23", required = true)
    val publicationDate: LocalDate,

    @field:Schema(description = "등록된 도서의 작가 아이디", example = "1", required = true)
    val authorId: Int,
)