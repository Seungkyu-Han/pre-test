package com.seungkyu.test.application.author.dto

data class CreateAuthorCommand(
    val name: String,
    val email: String
)