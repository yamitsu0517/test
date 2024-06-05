package com.example.shosekikun.presenter.book

import com.example.shosekikun.entity.Author
import com.example.shosekikun.entity.Category

data class BookCreatePresenter(
    val authors: List<Author>,
    val categories: List<Category>,
)
