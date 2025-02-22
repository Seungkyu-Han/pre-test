package com.seungkyu.test.controller.exceptionHandler.errorResponse

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

data class ErrorResponseDto(
    @JsonProperty
    @field:Schema(example = "다음과 같은 에러가 발생했습니다.", description = "에러 메시지")
    val message: String
)
