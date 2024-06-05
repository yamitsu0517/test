package com.example.shosekikun.entity

data class AuthorId(
    private val authorId: Int,
) {
    override fun toString(): String = authorId.toString()
    fun asInt(): Int = authorId
}
