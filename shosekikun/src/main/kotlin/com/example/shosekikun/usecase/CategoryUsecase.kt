package com.example.shosekikun.usecase

import com.example.shosekikun.entity.Category
import com.example.shosekikun.entity.CategoryId
import com.example.shosekikun.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryUsecase(
    private val categoryRepository: CategoryRepository
) {

    fun findAll(): List<Category> {
        return categoryRepository.findAll()
    }

    fun getCategoryBy(id: CategoryId): Category {
        return categoryRepository.findBy(id)

    }

    fun create(category: String) {
        categoryRepository.insert(category)
    }

    fun save(category: Category) {
        categoryRepository.save(category)
    }

    fun delete(id: CategoryId) {
        categoryRepository.delete(id)
    }
}
