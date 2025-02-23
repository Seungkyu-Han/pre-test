package com.seungkyu.test.application.author.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "작가 목록 응답")
data class AuthorInfoResponses(
    val authors: List<AuthorInfoResponse>
)
