package com.seungkyu.test.application.author.ports.input.service

import com.seungkyu.test.application.author.dto.*

interface AuthorService {

    fun createAuthor(createAuthorCommand: CreateAuthorCommand): CreateAuthorResponse

    fun authorInfo(authorId: Int): AuthorInfoResponse

    fun authorsInfo(): AuthorInfoResponses

    fun updateAuthor(
        id: Int,
        updateAuthorCommand: UpdateAuthorCommand): AuthorInfoResponse

    fun deleteAuthor(authorId: Int)

}