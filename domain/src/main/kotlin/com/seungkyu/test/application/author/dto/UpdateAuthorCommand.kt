package com.seungkyu.test.application.author.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull

@Schema(name = "작가 정보 변경 응답")
data class UpdateAuthorCommand(
    @field:NotNull(message = "이름을 입력해주세요")
    @field:Schema(description = "변경할 작가의 이름", example = "한승규", required = true)
    val name: String? = null,

    @field:NotNull(message = "이메일을 입력해주세요")
    @Email(message = "유효한 이메일 형식이 아닙니다")
    @field:Schema(description = "변경할 작가의 이메일", example = "trust1204@gmail.com", required = true)
    val email: String? = null
)