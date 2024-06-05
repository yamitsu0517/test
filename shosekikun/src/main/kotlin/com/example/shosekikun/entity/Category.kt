package com.example.shosekikun.entity

import java.time.LocalDateTime

data class Category(
    val id: CategoryId,
    val name: String,
    val createDate: LocalDateTime,
    val updateDate: LocalDateTime,
) {
    companion object {
        val EMPTY = Category(
            id = CategoryId(0),
            name = "",
            createDate = LocalDateTime.now(),
            updateDate = LocalDateTime.now(),
        )
    }
}