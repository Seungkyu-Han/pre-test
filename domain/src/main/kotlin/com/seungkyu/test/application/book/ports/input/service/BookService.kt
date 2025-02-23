package com.seungkyu.test.application.book.ports.input.service

import com.seungkyu.test.application.book.dto.*
import jakarta.validation.Valid

interface BookService {

    fun createBook(@Valid createBookCommand: CreateBookCommand): CreateBookResponse

    fun bookInfo(bookId: Int): BookInfoResponse

    fun booksInfo(page: Int, pageSize: Int, bookSortEnum: BookSortEnum): BookInfoResponses

    fun updateBook(
        id: Int,
        @Valid updateBookCommand: UpdateBookCommand
    ): BookInfoResponse

    fun deleteBook(bookId: Int)
}