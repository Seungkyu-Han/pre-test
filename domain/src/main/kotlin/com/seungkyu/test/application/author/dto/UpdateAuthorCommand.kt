package com.seungkyu.test.application.author.dto

data class UpdateAuthorCommand(
    val id: Int,
    val name: String,
    val email: String
)