package com.seungkyu.test.application.author.ports.input.service

import com.seungkyu.test.application.author.dto.*
import com.seungkyu.test.application.author.ports.output.repository.AuthorRepository
import com.seungkyu.test.domain.author.entity.Author
import com.seungkyu.test.domain.author.service.AuthorDomainService
import jakarta.validation.Valid
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthorServiceImpl(
    private val authorRepository: AuthorRepository,
    private val authorDomainService: AuthorDomainService
): AuthorService {

    @Transactional
    override fun createAuthor(@Valid createAuthorCommand: CreateAuthorCommand): CreateAuthorResponse {
        val author = authorDomainService.createAuthor(
            name = createAuthorCommand.name,
            email = createAuthorCommand.email,
        )

        val savedAuthor = authorRepository.save(author)

        return this.authorToCreateAuthorResponse(savedAuthor)
    }

    @Transactional(readOnly = true)
    override fun authorInfo(authorId: Int): AuthorInfoResponse {
        return authorToAuthorInfoResponse(
            authorRepository.findById(authorId)
        )
    }

    @Transactional(readOnly = true)
    override fun authorsInfo(): AuthorInfoResponses {
        return AuthorInfoResponses(
            authors = authorRepository.findAll().map(::authorToAuthorInfoResponse)
        )
    }

    @Transactional
    override fun updateAuthor(@Valid updateAuthorCommand: UpdateAuthorCommand): AuthorInfoResponse {
        val author = authorRepository.findById(updateAuthorCommand.id)

        authorDomainService.updateAuthor(
            author = author,
            name = updateAuthorCommand.name,
            email = updateAuthorCommand.email,
        )

        val savedUser = authorRepository.save(author)

        return authorToAuthorInfoResponse(savedUser)
    }

    @Transactional
    override fun deleteAuthor(authorId: Int) {
        authorRepository.findById(authorId)
        authorRepository.deleteById(authorId)
    }

    private fun authorToCreateAuthorResponse(author: Author): CreateAuthorResponse {
        return CreateAuthorResponse(
            name = author.name,
            email = author.email,
        )
    }

    private fun authorToAuthorInfoResponse(author: Author): AuthorInfoResponse {
        return AuthorInfoResponse(
            id = author.id!!,
            name = author.name,
            email = author.email,
        )
    }

}