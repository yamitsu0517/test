package com.example.shosekikun.entity

data class CategoryId(
    private val categoryId: Int,
) {
    override fun toString(): String = categoryId.toString()
}
