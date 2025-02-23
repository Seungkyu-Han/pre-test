package com.seungkyu.test.application.book.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "도서 정렬 key")
enum class BookSortEnum {

    title, isbn, publicationDate
}