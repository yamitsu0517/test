package com.example.shosekikun.dto

import java.time.LocalDateTime

data class CategoryDto(
    val categoryId: Int? = null,
    val name: String? = "",
    val createDate: LocalDateTime,
    val updateDate: LocalDateTime,
)
