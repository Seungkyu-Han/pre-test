package com.seungkyu.test.application.book.dao

import com.seungkyu.test.domain.book.entity.Book

data class BooksInfoDao(
    val books: List<Book>,
    val totalElement: Long
)
