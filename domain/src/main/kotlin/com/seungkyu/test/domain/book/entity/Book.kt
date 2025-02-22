package com.seungkyu.test.domain.book.entity

import com.seungkyu.test.domain.book.exception.BookDomainException
import java.time.LocalDate

data class Book(
    val id: Int?,
    val title: String,
    val description: String,
    val isbn: String,
    val publicationDate: LocalDate,
    val authorId: Int,
){
    fun validateIsbn(){
        validateIsbnLength()
        validateIsbnLang()
        validateIsbnCheckDigit()
    }

    private fun validateIsbnLength(){
        if(isbn.length != 10)
            throw BookDomainException("ISBN의 길이가 유효하지 않습니다")
    }

    private fun validateIsbnLang(){
        val langCode = isbn.substring(0, 3).toInt()
        if(langCode !in 100..900)
            throw BookDomainException("ISBN의 언어코드가 유효하지 않습니다.")
    }

    private fun validateIsbnCheckDigit(){
        if (isbn.last() != '0')
            throw BookDomainException("ISBN의 체크 digit이 유효하지 않습니다.")
    }
}