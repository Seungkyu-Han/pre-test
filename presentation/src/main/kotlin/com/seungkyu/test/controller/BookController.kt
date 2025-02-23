package com.seungkyu.test.controller

import com.seungkyu.test.application.author.dto.*
import com.seungkyu.test.application.book.dto.*
import com.seungkyu.test.application.book.ports.input.service.BookService
import com.seungkyu.test.controller.exceptionHandler.errorResponse.ErrorResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
@Tag(name = "도서 API", description = "도서와 관련된 CRUD API입니다.")
class BookController(
    private val bookService: BookService
) {

    @PostMapping
    @Operation(
        summary = "도서를 등록하는 API입니다.",
        description = "존재하지 않는 작가로 도서를 등록시 404 에러가 반환됩니다.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "도서 생성 요청",
            required = true,
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)],
        )
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "등록에 성공했습니다.",
            content = [Content(schema = Schema(implementation = CreateBookResponse::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)]),
        ApiResponse(responseCode = "404", description = "해당 작가가 존재하지 않습니다.",
            content = [Content(schema = Schema(implementation = ErrorResponseDto::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)])
    )
    fun postBook(@RequestBody createBookCommand: CreateBookCommand): ResponseEntity<CreateBookResponse> =
        ResponseEntity.ok(bookService.createBook(createBookCommand))

    @GetMapping
    @Operation(
        summary = "도서의 전체 목록을 조회하는 API입니다.",
        description = "아이디를 포함한 전체 도서의 정보가 출력됩니다.\n" +
                "도서가 존재하지 않는 경우 404에러가 아닌 빈 배열이 출력됩니다."
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회에 성공했습니다.",
            content = [Content(schema = Schema(implementation = BookInfoResponses::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)])
    )
    fun getBooks(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) sortKey: BookSortEnum = BookSortEnum.publicationDate,
    ): ResponseEntity<BookInfoResponses> =
        ResponseEntity.ok(bookService.booksInfo(
            page = page,
            pageSize = pageSize,
            bookSortEnum = sortKey,
        ))

    @GetMapping("/{id}")
    @Operation(
        summary = "도서의 상세 정보를 조회하는 API입니다.",
        description = "아이디를 포함한 전체 도서의 정보가 출력됩니다.\n" +
                "존재하지 않는 도서는 404에러가 출력됩니다."
    )
    @Parameters(
        Parameter(name = "id", description = "조회할 도서의 id", example = "1", required = true),
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회에 성공했습니다.",
            content = [Content(schema = Schema(implementation = BookInfoResponse::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)]),
        ApiResponse(responseCode = "404", description = "존재하지 않는 도서입니다.",
            content = [Content(schema = Schema(implementation = ErrorResponseDto::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)]),
    )
    fun getBook(@PathVariable id: Int): ResponseEntity<BookInfoResponse> =
        ResponseEntity.ok(bookService.bookInfo(bookId = id))

    @PutMapping("/{id}")
    @Operation(
        summary = "도서의 정보를 수정하는 API입니다.",
        description = "도서의 정보를 수정하는 API이며, 변경하지 않는 항목이더라도 입력해주셔야 합니다."
    )
    @Parameters(
        Parameter(name = "id", description = "수정할 도서의 id", example = "1", required = true),
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "변경에 성공했습니다.",
            content = [Content(schema = Schema(implementation = BookInfoResponse::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)]),
        ApiResponse(responseCode = "404", description = "존재하지 않는 도서입니다.",
            content = [Content(schema = Schema(implementation = ErrorResponseDto::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)])
    )
    fun putBook(
        @PathVariable id: Int,
        @RequestBody updateBookCommand: UpdateBookCommand
    ): ResponseEntity<BookInfoResponse> =
        ResponseEntity.ok(bookService.updateBook(id = id, updateBookCommand = updateBookCommand))

    @DeleteMapping("/{id}")
    @Operation(
        summary = "도서의 정보를 삭제하는 API입니다.",
        description = "해당 도서의 정보를 삭제합니다."
    )
    @Parameters(
        Parameter(name = "id", description = "삭제할 도서의 id", example = "1", required = true),
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "삭제에 성공했습니다.",
            content = [Content(schema = Schema(implementation = AuthorInfoResponse::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)]),
        ApiResponse(responseCode = "404", description = "존재하지 않는 작가입니다.",
            content = [Content(schema = Schema(implementation = ErrorResponseDto::class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)])
    )
    fun deleteAuthor(@PathVariable id: Int): ResponseEntity<Unit> =
        ResponseEntity.ok(bookService.deleteBook(bookId = id))

}