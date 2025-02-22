package com.seungkyu.test.application.author.dto

import jakarta.validation.constraints.Email

data class UpdateAuthorCommand(
    val id: Int,
    val name: String,
    @Email(message = "유효한 이메일 형식이 아닙니다")
    val email: String
)