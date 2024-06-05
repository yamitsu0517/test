package com.example.shosekikun.presenter.category

import com.example.shosekikun.entity.Category

class CategoryPresenter(
    private val categories: List<Category>
) {
    fun getCategories() = categories
}