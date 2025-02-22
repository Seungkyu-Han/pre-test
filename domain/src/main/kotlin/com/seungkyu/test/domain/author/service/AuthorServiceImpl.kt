package com.seungkyu.test.domain.author.service

import com.seungkyu.test.domain.author.entity.Author
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthorServiceImpl: AuthorService {

    override fun createAuthor(name: String, email: String): Author =
        Author(
            id = UUID.randomUUID(),
            name = name,
            email = email
        )
}