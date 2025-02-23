package com.seungkyu.test.application.author.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Schema(name = "작가 생성 요청")
data class CreateAuthorCommand(
    @field:Schema(description = "작가의 이름", example = "한승규", required = true)
    @field:NotBlank(message = "이름을 입력해주세요")
    val name: String? = null,

    @field:NotBlank(message = "이메일을 입력해주세요")
    @field:Email(message = "유효한 이메일 형식이 아닙니다")
    @field:Schema(description = "작가의 이메일", example = "trust1204@gmail.com", required = true)
    val email: String? = null
)