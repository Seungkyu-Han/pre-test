package com.seungkyu.test.book.repository

import com.seungkyu.test.book.entity.BookEntity
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import java.awt.print.Pageable

interface BookJPARepository: JpaRepository<BookEntity, Int> {

    fun findAll(pageable: Pageable): Page<BookEntity>
}