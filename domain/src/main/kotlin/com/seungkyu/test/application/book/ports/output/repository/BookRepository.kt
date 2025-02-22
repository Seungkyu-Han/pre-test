package com.seungkyu.test.application.book.ports.output.repository

import com.seungkyu.test.application.book.dao.BooksInfoDao
import com.seungkyu.test.application.book.dto.BookSortEnum
import com.seungkyu.test.domain.book.entity.Book

interface BookRepository {

    fun save(book: Book): Book

    fun findById(id: Int): Book

    fun findAll(page: Int, pageSize: Int, bookSortEnum: BookSortEnum): BooksInfoDao

    fun deleteById(id: Int)
}