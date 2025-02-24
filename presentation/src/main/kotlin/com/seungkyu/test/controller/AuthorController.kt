package com.seungkyu.test.controller

import com.seungkyu.test.application.author.dto.*
import com.seungkyu.test.application.author.ports.input.service.AuthorService
import com.seungkyu.test.controller.exceptionHandler.errorResponse.ErrorResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
            description = "작가 생성 요청",
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
    fun postAuthor(@RequestBody @Valid createAuthorCommand: CreateAuthorCommand): ResponseEntity<CreateAuthorResponse> =
        ResponseEntity.ok(authorService.createAuthor(createAuthorCommand))

    @GetMapping
    @Operation(
        summary = "작가의 전체 목록을 조회하는 API입니다.",
        description = "아이디를 포함한 전체 작가의 정보가 출력됩니다.\n" +
                "작가가 존재하지 않는 경우 404에러가 아닌 빈 배열이 출력됩니다."
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회에 성공했습니다.",
            content = [Content(schema = Schema(implementation = AuthorInfoResponses::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)])
    )
    fun getAuthors(): ResponseEntity<AuthorInfoResponses> =
        ResponseEntity.ok(authorService.authorsInfo())

    @GetMapping("/{id}")
    @Operation(
        summary = "작가의 상세 정보를 조회하는 API입니다.",
        description = "아이디를 포함한 전체 작가의 정보가 출력됩니다.\n" +
                "존재하지 않는 작가는 404에러가 출력됩니다."
    )
    @Parameters(
        Parameter(name = "id", description = "조회할 작가의 id", example = "1", required = true),
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회에 성공했습니다.",
            content = [Content(schema = Schema(implementation = AuthorInfoResponse::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)]),
        ApiResponse(responseCode = "404", description = "존재하지 않는 작가입니다.",
            content = [Content(schema = Schema(implementation = ErrorResponseDto::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)]),
    )
    fun getAuthor(@PathVariable id: Int): ResponseEntity<AuthorInfoResponse> =
        ResponseEntity.ok(authorService.authorInfo(authorId = id))

    @PutMapping("/{id}")
    @Operation(
        summary = "작가의 정보를 수정하는 API입니다.",
        description = "작가의 정보를 수정하는 API이며, 변경하지 않는 항목이더라도 입력해주셔야 합니다."
    )
    @Parameters(
        Parameter(name = "id", description = "수정할 작가의 id", example = "1", required = true),
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "변경에 성공했습니다.",
            content = [Content(schema = Schema(implementation = AuthorInfoResponse::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)]),
        ApiResponse(responseCode = "404", description = "존재하지 않는 작가입니다.",
            content = [Content(schema = Schema(implementation = ErrorResponseDto::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)]),
        ApiResponse(responseCode = "409", description = "중복되는 이메일이 존재합니다.",
            content = [Content(schema = Schema(implementation = ErrorResponseDto::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)]),
    )
    fun putAuthor(
        @PathVariable id: Int,
        @RequestBody @Valid updateAuthorCommand: UpdateAuthorCommand): ResponseEntity<AuthorInfoResponse> =
        ResponseEntity.ok(authorService.updateAuthor(id = id, updateAuthorCommand = updateAuthorCommand))

    @DeleteMapping("/{id}")
    @Operation(
        summary = "작가의 정보를 삭제하는 API입니다.",
        description = "작가의 정보를 삭제하는 API이며, \n" +
                "해당 작가의 도서 정보가 남아있을 경우 삭제가 불가능합니다."
    )
    @Parameters(
        Parameter(name = "id", description = "삭제할 작가의 id", example = "1", required = true),
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "삭제에 성공했습니다.",
            content = [Content(schema = Schema(implementation = AuthorInfoResponse::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)]),
        ApiResponse(responseCode = "404", description = "존재하지 않는 작가입니다.",
            content = [Content(schema = Schema(implementation = ErrorResponseDto::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)]),
        ApiResponse(responseCode = "409", description = "남아있는 도서 정보가 있기 때문에 삭제가 불가능합니다",
            content = [Content(schema = Schema(implementation = ErrorResponseDto::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)]),
    )
    fun deleteAuthor(@PathVariable id: Int): ResponseEntity<Unit> =
        ResponseEntity.ok(authorService.deleteAuthor(authorId = id))

}