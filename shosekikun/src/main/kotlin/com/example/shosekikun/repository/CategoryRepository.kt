package com.example.shosekikun.repository

import com.example.shosekikun.entity.Category
import com.example.shosekikun.entity.CategoryId
import org.springframework.stereotype.Component

interface CategoryRepository {
    fun findAll(): List<Category>

    fun findBy(id: CategoryId): Category

    fun save(category: Category)

    fun delete(categoryId: CategoryId)
}