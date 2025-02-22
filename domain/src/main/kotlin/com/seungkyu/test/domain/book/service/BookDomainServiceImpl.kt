package com.seungkyu.test.domain.book.service

import com.seungkyu.test.domain.book.entity.Book
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class BookDomainServiceImpl: BookDomainService {

    override fun createBook(
        title: String,
        description: String,
        isbn: String,
        publicationDate: LocalDate,
        authorId: Int
    ): Book {
        val book = Book(
            id = null,
            title = title,
            description = description,
            isbn = isbn,
            publicationDate = publicationDate,
            authorId = authorId
        )

        book.validateIsbn()

        return book
    }

    override fun updateBook(
        id: Int,
        title: String,
        description: String,
        isbn: String,
        publicationDate: LocalDate,
        authorId: Int
    ): Book {
        val book = Book(
            id = id,
            title = title,
            description = description,
            isbn = isbn,
            publicationDate = publicationDate,
            authorId = authorId
        )

        book.validateIsbn()

        return book
    }
}