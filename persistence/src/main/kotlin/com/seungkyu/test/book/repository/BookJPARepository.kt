package com.seungkyu.test.book.repository

import com.seungkyu.test.author.entity.AuthorEntity
import com.seungkyu.test.book.entity.BookEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BookJPARepository: JpaRepository<BookEntity, Int> {

    fun existsByAuthor(author: AuthorEntity): Boolean
}