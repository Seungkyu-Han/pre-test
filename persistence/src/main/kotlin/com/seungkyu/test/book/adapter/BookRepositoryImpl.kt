package com.seungkyu.test.book.adapter

import com.seungkyu.test.application.book.dao.BooksInfoDao
import com.seungkyu.test.application.book.dto.BookSortEnum
import com.seungkyu.test.application.book.exception.BookErrorCode
import com.seungkyu.test.application.book.exception.BookException
import com.seungkyu.test.application.book.ports.output.repository.BookRepository
import com.seungkyu.test.author.entity.AuthorEntity
import com.seungkyu.test.book.entity.BookEntity
import com.seungkyu.test.book.repository.BookJPARepository
import com.seungkyu.test.domain.book.entity.Book
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(
    private val bookJPARepository: BookJPARepository
): BookRepository {

    override fun save(book: Book): Book {
        return try {
            bookEntityToBook(
                bookJPARepository.save(
                    bookToBookEntity(book)
                )
            )
        }catch (dataIntegrityViolationException: DataIntegrityViolationException){
            throw BookException(BookErrorCode.AUTHOR_NOT_FOUND)
        }
    }

    override fun findById(id: Int): Book =
        bookEntityToBook(
            bookJPARepository.findById(id)
                .orElseThrow { BookException(BookErrorCode.BOOK_NOT_FOUND) }
        )

    override fun findAll(page: Int, pageSize: Int, bookSortEnum: BookSortEnum): BooksInfoDao {
        val pageBooks = bookJPARepository.findAll(
            PageRequest.of(page, pageSize, Sort.by(bookSortEnum.name))
        )
        return BooksInfoDao(
            books = pageBooks.toList().map(::bookEntityToBook),
            totalElement = pageBooks.totalElements
        )
    }

    override fun deleteById(id: Int) =
        bookJPARepository.deleteById(id)

    private fun bookToBookEntity(book: Book): BookEntity {
        return BookEntity(
            id = book.id,
            title = book.title,
            description = book.description,
            isbn = book.isbn,
            publicationDate = book.publicationDate,
            author = AuthorEntity(
                id = book.authorId,
                name = "",
                email = ""
            )
        )
    }

    private fun bookEntityToBook(bookEntity: BookEntity): Book {
        return Book(
            id = bookEntity.id,
            title = bookEntity.title,
            description = bookEntity.description,
            isbn = bookEntity.isbn,
            publicationDate = bookEntity.publicationDate,
            authorId = bookEntity.author.id!!
        )
    }
}