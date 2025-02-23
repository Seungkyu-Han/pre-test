package com.seungkyu.test.domain.book.entity

import com.seungkyu.test.domain.book.exception.BookDomainException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.UUID

class BookTest{

    @Nested
    inner class ValidateIsbn{

        @Test
        @DisplayName("유효한 ISBN으로 테스트")
        fun testValidIsbn(){
            //given
            val book = Book(
                id = 1,
                isbn = "1234567890",
                publicationDate = LocalDate.now(),
                title = UUID.randomUUID().toString(),
                description = UUID.randomUUID().toString(),
                authorId = 1
            )

            //when && then
            book.validateIsbn()
        }

        @Test
        @DisplayName("유효하지 않은 언어코드 사용")
        fun testInvalidLangCode1(){
            //given
            val book = Book(
                id = 1,
                isbn = "0994567890",
                publicationDate = LocalDate.now(),
                title = UUID.randomUUID().toString(),
                description = UUID.randomUUID().toString(),
                authorId = 1
            )

            //when && then
            Assertions.assertThrows(
                BookDomainException::class.java
            ){
                book.validateIsbn()
            }
        }

        @Test
        @DisplayName("유효하지 않은 언어코드 사용")
        fun testInvalidLangCode2(){
            //given
            val book = Book(
                id = 1,
                isbn = "9994567890",
                publicationDate = LocalDate.now(),
                title = UUID.randomUUID().toString(),
                description = UUID.randomUUID().toString(),
                authorId = 1
            )

            //when && then
            Assertions.assertThrows(
                BookDomainException::class.java
            ){
                book.validateIsbn()
            }
        }

        @Test
        @DisplayName("유효하지 않은 check digit 사용")
        fun testInvalidCheckDigit(){
            //given
            val book = Book(
                id = 1,
                isbn = "1004567891",
                publicationDate = LocalDate.now(),
                title = UUID.randomUUID().toString(),
                description = UUID.randomUUID().toString(),
                authorId = 1
            )

            //when && then
            Assertions.assertThrows(
                BookDomainException::class.java
            ){
                book.validateIsbn()
            }
        }

        @Test
        @DisplayName("유효하지 않은 isbn 길이")
        fun testInvalidIsbnLength(){
            //given
            val book = Book(
                id = 1,
                isbn = "10045678910",
                publicationDate = LocalDate.now(),
                title = UUID.randomUUID().toString(),
                description = UUID.randomUUID().toString(),
                authorId = 1
            )

            //when && then
            Assertions.assertThrows(
                BookDomainException::class.java
            ){
                book.validateIsbn()
            }
        }
    }
}