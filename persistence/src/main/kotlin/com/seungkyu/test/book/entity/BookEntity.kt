package com.seungkyu.test.book.entity

import com.seungkyu.test.author.entity.AuthorEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity(name = "books")
data class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    val title: String,

    val description: String,

    @Column(unique = true)
    val isbn: String,

    val publicationDate: LocalDate,

    @ManyToOne
    val author: AuthorEntity,
)
