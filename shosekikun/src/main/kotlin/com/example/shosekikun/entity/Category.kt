package com.example.shosekikun.entity

data class Category(
    val id: CategoryId,
    val name: String,
) {
    companion object {
        val EMPTY = Category(
            id = CategoryId(0),
            name = ""
        )
    }
}