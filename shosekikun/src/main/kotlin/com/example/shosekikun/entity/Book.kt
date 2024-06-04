package com.example.shosekikun.entity

data class Book(
    val id: BookId,
    val title: String,
    val price: Int,
    val authorId: AuthorId,
    val categoryId: CategoryId,
) {
    companion object {
        val EMPTY = Book(
            id = BookId(0),
            title = "",
            price = 0,
            authorId = AuthorId(0),
            categoryId = CategoryId(0)
        )
    }
}
