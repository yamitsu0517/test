package com.example.shosekikun.entity

data class CategoryId(
    private val categoryId: Int,
) {
    fun asInt(): Int = categoryId
}
