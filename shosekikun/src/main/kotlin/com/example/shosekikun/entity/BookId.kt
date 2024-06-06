package com.example.shosekikun.entity

data class BookId(
    private val bookId: Int,
) {
    fun asInt() = bookId
}
