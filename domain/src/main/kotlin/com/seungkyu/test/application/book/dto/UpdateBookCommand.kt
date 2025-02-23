package com.seungkyu.test.application.book.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

@Schema(name = "도서 정보 변경 요청")
data class UpdateBookCommand(

    @field:NotNull(message = "이름을 입력해주세요")
    @field:Schema(description = "변경할 도서의 이름", example = "도서", required = true)
    val title: String? = null,

    @field:NotNull(message = "설명을 입력해주세요")
    @field:Schema(description = "변경할 도서의 설명", example = "도서의 설명", required = true)
    val description: String? = null,

    @field:NotNull(message = "isbn을 입력해주세요")
    @field:Schema(description = "변경할 도서의 isbn", example = "1000000000", required = true)
    val isbn: String? = null,

    @field:NotNull(message = "발행일을 입력해주세요")
    @field:Schema(description = "변경할 도서의 발행일", example = "2025-02-23", required = true)
    val publicationDate: LocalDate? = null,

    @field:NotNull(message = "작가의 id를 입력해주세요")
    @field:Schema(description = "변경할 도서의 작가 id", example = "1", required = true)
    val authorId: Int? = null,
)