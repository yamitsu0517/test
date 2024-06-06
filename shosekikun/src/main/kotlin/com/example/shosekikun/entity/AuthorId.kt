package com.example.shosekikun.entity

data class AuthorId(
    private val authorId: Int,
) {
    fun asInt(): Int = authorId
}
