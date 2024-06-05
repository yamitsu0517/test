package com.example.shosekikun.repository

import com.example.shosekikun.entity.Category
import com.example.shosekikun.entity.CategoryId

interface CategoryRepository {
    fun findAll(): List<Category>

    fun findBy(id: CategoryId): Category

    fun insert(categoryName: String)

    fun save(category: Category)

    fun delete(categoryId: CategoryId)
}