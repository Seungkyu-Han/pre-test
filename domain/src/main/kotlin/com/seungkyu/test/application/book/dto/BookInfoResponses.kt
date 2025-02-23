package com.seungkyu.test.application.book.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "도서 목록 응답")
data class BookInfoResponses(

    val books: List<BookInfoResponse>,

    @field:Schema(description = "등록된 도서의 총 개수", example = "1", required = true)
    val totalElement: Long
)
