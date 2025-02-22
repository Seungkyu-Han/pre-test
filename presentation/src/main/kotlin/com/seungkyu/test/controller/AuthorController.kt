package com.seungkyu.test.controller

import com.seungkyu.test.application.author.dto.CreateAuthorCommand
import com.seungkyu.test.application.author.dto.CreateAuthorResponse
import com.seungkyu.test.application.author.ports.input.service.AuthorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authors")
class AuthorController(
    private val authorService: AuthorService
) {
    @PostMapping
    fun postAuthor(@RequestBody createAuthorCommand: CreateAuthorCommand): ResponseEntity<CreateAuthorResponse> =
        ResponseEntity.ok(authorService.createAuthor(createAuthorCommand))
}