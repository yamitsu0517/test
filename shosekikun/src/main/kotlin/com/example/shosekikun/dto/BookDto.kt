package com.example.shosekikun.dto

import java.time.LocalDateTime

data class BookDto(
    val id: Int? = null,
    val title: String? = null,
    val price: Int? = null,
    val authorId: Int? = null,
    val authorName: String? = null,
    val categoryId: Int? = null,
    val categoryName: String? = null,
    val createdAt: LocalDateTime?,
    val modifiedAt: LocalDateTime?,
)
