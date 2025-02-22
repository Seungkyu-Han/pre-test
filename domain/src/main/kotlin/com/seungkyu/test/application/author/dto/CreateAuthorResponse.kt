package com.seungkyu.test.application.author.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "작가 생성 응답")
data class CreateAuthorResponse(
    @field:Schema(description = "등록된 작가의 이름", example = "한승규", required = true)
    val name: String,
    @field:Schema(description = "등록된 작가의 이메일", example = "trust1204@gmail.com", required = true)
    val email: String
)