package com.example.shosekikun.entity

data class Author(
    val id: AuthorId,
    val name: String,
) {
    companion object {
        val EMPTY = Author(
            id = AuthorId(0),
            name = "",
        )
    }
}
