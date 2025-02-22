package com.seungkyu.test.author.adapter

import com.seungkyu.test.application.author.exception.AuthorErrorCode
import com.seungkyu.test.application.author.exception.AuthorException
import com.seungkyu.test.application.author.ports.output.repository.AuthorRepository
import com.seungkyu.test.author.entity.AuthorEntity
import com.seungkyu.test.author.repository.AuthorJPARepository
import com.seungkyu.test.domain.author.entity.Author
import org.springframework.stereotype.Repository

@Repository
class AuthorRepositoryImpl(
    private val authorJPARepository: AuthorJPARepository
): AuthorRepository {

    override fun save(author: Author): Author =
        authorEntityToAuthor(
            authorJPARepository.save(
                authorToAuthorEntity(author)
            )
        )

    override fun findById(id: Int): Author =
        authorEntityToAuthor(
            authorJPARepository.findById(id)
                .orElseThrow { (AuthorException(AuthorErrorCode.AUTHOR_NOT_FOUND)) }
        )


    override fun findAll(): List<Author> =
        authorJPARepository.findAll().map(::authorEntityToAuthor)

    override fun deleteById(id: Int) =
        authorJPARepository.deleteById(id)

    private fun authorToAuthorEntity(author: Author): AuthorEntity {
        return AuthorEntity(
            id = author.id,
            name = author.name,
            email = author.email
        )
    }

    private fun authorEntityToAuthor(authorEntity: AuthorEntity): Author {
        return Author(
            id = authorEntity.id,
            name = authorEntity.name,
            email = authorEntity.email
        )
    }
}