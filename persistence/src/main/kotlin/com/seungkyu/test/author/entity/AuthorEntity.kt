package com.seungkyu.test.author.entity

import jakarta.persistence.*

@Entity(name = "authors")
data class AuthorEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    val name: String,

    @Column(unique = true)
    val email: String
)
