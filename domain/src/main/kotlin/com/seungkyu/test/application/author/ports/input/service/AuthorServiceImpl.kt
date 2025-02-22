package com.seungkyu.test.application.author.ports.input.service

import com.seungkyu.test.application.author.dto.AuthorInfoResponse
import com.seungkyu.test.application.author.dto.CreateAuthorResponse
import com.seungkyu.test.application.author.dto.CreateAuthorCommand
import com.seungkyu.test.application.author.dto.UpdateAuthorCommand
import com.seungkyu.test.application.author.exception.AuthorErrorCode
import com.seungkyu.test.application.author.exception.AuthorException
import com.seungkyu.test.application.author.ports.output.repository.AuthorRepository
import com.seungkyu.test.domain.author.entity.Author
import com.seungkyu.test.domain.author.service.AuthorDomainService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthorServiceImpl(
    private val authorRepository: AuthorRepository,
    private val authorDomainService: AuthorDomainService
): AuthorService {

    @Transactional
    override fun createAuthor(createAuthorCommand: CreateAuthorCommand): CreateAuthorResponse {
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
                .orElseThrow { (AuthorException(AuthorErrorCode.AUTHOR_NOT_FOUND)) }
        )
    }

    @Transactional(readOnly = true)
    override fun authorsInfo(): List<AuthorInfoResponse> {
        return authorRepository.findAll().map(::authorToAuthorInfoResponse)
    }

    @Transactional
    override fun updateAuthor(updateAuthorCommand: UpdateAuthorCommand): AuthorInfoResponse {
        val author = authorRepository.findById(updateAuthorCommand.id)
            .orElseThrow { (AuthorException(AuthorErrorCode.AUTHOR_NOT_FOUND)) }

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
            .orElseThrow { (AuthorException(AuthorErrorCode.AUTHOR_NOT_FOUND)) }
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