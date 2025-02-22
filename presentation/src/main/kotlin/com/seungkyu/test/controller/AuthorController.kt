package com.seungkyu.test.controller

import com.seungkyu.test.application.author.dto.CreateAuthorCommand
import com.seungkyu.test.application.author.dto.CreateAuthorResponse
import com.seungkyu.test.application.author.ports.input.service.AuthorService
import com.seungkyu.test.controller.exceptionHandler.errorResponse.ErrorResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authors")
@Tag(name = "작가 API", description = "작가와 관련된 CRUD API입니다.")
class AuthorController(
    private val authorService: AuthorService
) {
    @PostMapping
    @Operation(
        summary = "작가를 등록하는 API입니다.",
        description = "이미 등록된 이메일 사용시 conflict 에러가 발생합니다.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "작가 등록 DTO",
            required = true,
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)],
        )
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "등록에 성공했습니다.",
            content = [Content(schema = Schema(implementation = CreateAuthorResponse::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)]),
        ApiResponse(responseCode = "409", description = "중복되는 이메일이 존재합니다.",
            content = [Content(schema = Schema(implementation = ErrorResponseDto::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)])
    )
    fun postAuthor(@RequestBody createAuthorCommand: CreateAuthorCommand): ResponseEntity<CreateAuthorResponse> =
        ResponseEntity.ok(authorService.createAuthor(createAuthorCommand))
}