package com.example.shosekikun.dto

import java.time.LocalDateTime

data class AuthorDto(
    val id: Int? = null,
    val name: String? = "",
    val createdAt: LocalDateTime?,
    val modifiedAt: LocalDateTime?,
)
