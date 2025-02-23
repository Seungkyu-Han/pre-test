package com.seungkyu.test.author.repository

import com.seungkyu.test.author.entity.AuthorEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AuthorJPARepository: JpaRepository<AuthorEntity, Int>{

    fun findByEmail(email: String): Optional<AuthorEntity>
}