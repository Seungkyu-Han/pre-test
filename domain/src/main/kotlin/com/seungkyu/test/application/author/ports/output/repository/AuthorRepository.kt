package com.seungkyu.test.application.author.ports.output.repository

import com.seungkyu.test.domain.author.entity.Author
import java.util.*

interface AuthorRepository {

    fun save(author: Author): Author

    fun findById(id: Int): Author

    fun findAll(): List<Author>

    fun deleteById(id: Int)

    fun findByEmail(email: String): Optional<Author>
}