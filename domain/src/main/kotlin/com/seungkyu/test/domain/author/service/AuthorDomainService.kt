package com.seungkyu.test.domain.author.service

import com.seungkyu.test.domain.author.entity.Author

interface AuthorDomainService {

    fun createAuthor(name: String, email: String): Author
    fun updateAuthor(author: Author, name: String, email: String)
}