package com.seungkyu.test.domain.author.service

import com.seungkyu.test.domain.author.entity.Author
import org.springframework.stereotype.Service

@Service
class AuthorDomainServiceImpl: AuthorDomainService {

    override fun createAuthor(name: String, email: String): Author =
        Author(
            id = null,
            name = name,
            email = email
        )
}