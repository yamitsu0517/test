package com.example.shosekikun.entity

data class BookId(
    private val bookId: Int,
) {
    override fun toString(): String = bookId.toString()
    fun asInt() = bookId
}
