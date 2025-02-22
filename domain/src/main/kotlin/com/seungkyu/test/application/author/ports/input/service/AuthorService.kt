package com.seungkyu.test.application.author.ports.input.service

import com.seungkyu.test.application.author.dto.AuthorInfoResponse
import com.seungkyu.test.application.author.dto.CreateAuthorCommand
import com.seungkyu.test.application.author.dto.CreateAuthorResponse
import com.seungkyu.test.application.author.dto.UpdateAuthorCommand

interface AuthorService {

    fun createAuthor(createAuthorCommand: CreateAuthorCommand): CreateAuthorResponse

    fun authorInfo(authorId: Int): AuthorInfoResponse

    fun authorsInfo(): List<AuthorInfoResponse>

    fun updateAuthor(updateAuthorCommand: UpdateAuthorCommand): AuthorInfoResponse

    fun deleteAuthor(authorId: Int)

}