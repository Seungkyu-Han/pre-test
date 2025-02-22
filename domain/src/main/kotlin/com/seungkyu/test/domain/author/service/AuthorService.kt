package com.seungkyu.test.domain.author.service

import com.seungkyu.test.domain.author.entity.Author

interface AuthorService {

    fun createAuthor(name: String, email: String): Author
}