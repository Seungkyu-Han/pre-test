package com.seungkyu.test.domain.book.service

import com.seungkyu.test.domain.book.entity.Book
import java.time.LocalDate

interface BookDomainService {

    fun createBook(
        title: String,
        description: String,
        isbn: String,
        publicationDate: LocalDate,
        authorId: Int,
    ): Book

    fun updateBook(
        id: Int,
        title: String,
        description: String,
        isbn: String,
        publicationDate: LocalDate,
        authorId: Int
    ): Book
}