package com.seungkyu.test.application.book.ports.input.service

import com.seungkyu.test.application.book.dto.*
import com.seungkyu.test.application.book.exception.BookErrorCode
import com.seungkyu.test.application.book.exception.BookException
import com.seungkyu.test.application.book.ports.output.repository.BookRepository
import com.seungkyu.test.domain.book.entity.Book
import com.seungkyu.test.domain.book.exception.BookDomainException
import com.seungkyu.test.domain.book.service.BookDomainService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookServiceImpl(
    private val bookRepository: BookRepository,
    private val bookDomainService: BookDomainService
): BookService {

    @Transactional
    override fun createBook(createBookCommand: CreateBookCommand): CreateBookResponse {

        val book = try{
             bookDomainService.createBook(
                title = createBookCommand.title,
                isbn = createBookCommand.isbn,
                authorId = createBookCommand.authorId,
                description = createBookCommand.description,
                publicationDate = createBookCommand.publicationDate
            )
        } catch(bookDomainException: BookDomainException) {
            throw BookException(BookErrorCode.INVALID_ISBN)
        }

        val savedBook = bookRepository.save(book)

        return bookToCreateBookResponse(savedBook)
    }

    @Transactional(readOnly = true)
    override fun bookInfo(bookId: Int): BookInfoResponse {
        return bookToBookInfoResponse(
            bookRepository.findById(bookId)
        )
    }

    @Transactional(readOnly = true)
    override fun booksInfo(page: Int, pageSize: Int, bookSortEnum: BookSortEnum): BookInfoResponses {
        val booksInfoDao = bookRepository.findAll(page, pageSize, bookSortEnum)

        val bookInfoResponses = BookInfoResponses(
            books = booksInfoDao.books.map(::bookToBookInfoResponse),
            totalElement = booksInfoDao.totalElement
        )

        return bookInfoResponses
    }

    @Transactional
    override fun updateBook(id: Int, updateBookCommand: UpdateBookCommand): BookInfoResponse {
        val book = bookRepository.findById(id)

        val newBook = try {
            bookRepository.save(bookDomainService.updateBook(
                id = book.id!!,
                title = updateBookCommand.title,
                isbn = updateBookCommand.isbn,
                authorId = updateBookCommand.authorId,
                description = updateBookCommand.description,
                publicationDate = updateBookCommand.publicationDate)
            )
        } catch(bookDomainException: BookDomainException) {
            throw BookException(BookErrorCode.INVALID_ISBN)
        }

        return bookToBookInfoResponse(newBook)
    }

    @Transactional
    override fun deleteBook(bookId: Int) {
        bookRepository.findById(bookId)
        bookRepository.deleteById(bookId)
    }

    private fun bookToCreateBookResponse(book: Book): CreateBookResponse {
        return CreateBookResponse(
            title = book.title,
            isbn = book.isbn,
            authorId = book.authorId,
            description = book.description,
            publicationDate = book.publicationDate
        )
    }

    private fun bookToBookInfoResponse(book: Book): BookInfoResponse {
        return BookInfoResponse(
            id = book.id!!,
            title = book.title,
            isbn = book.isbn,
            authorId = book.authorId,
            description = book.description,
            publicationDate = book.publicationDate,
        )
    }
}