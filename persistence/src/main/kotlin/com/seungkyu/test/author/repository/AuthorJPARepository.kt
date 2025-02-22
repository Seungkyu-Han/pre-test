package com.seungkyu.test.author.repository

import com.seungkyu.test.author.entity.AuthorEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorJPARepository: JpaRepository<AuthorEntity, Int>