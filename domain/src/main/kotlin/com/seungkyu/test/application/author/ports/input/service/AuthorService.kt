package com.seungkyu.test.application.author.ports.input.service

import com.seungkyu.test.application.author.dto.*
import jakarta.validation.Valid

interface AuthorService {

    fun createAuthor(@Valid createAuthorCommand: CreateAuthorCommand): CreateAuthorResponse

    fun authorInfo(authorId: Int): AuthorInfoResponse

    fun authorsInfo(): AuthorInfoResponses

    fun updateAuthor(
        id: Int,
        @Valid updateAuthorCommand: UpdateAuthorCommand): AuthorInfoResponse

    fun deleteAuthor(authorId: Int)

}