package com.example.shosekikun.usecase

import com.example.shosekikun.common.DataNotFoundException
import com.example.shosekikun.entity.Category
import com.example.shosekikun.entity.CategoryId
import com.example.shosekikun.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class CategoryUsecase(
    private val categoryRepository: CategoryRepository
) {

    fun findAll(): List<Category> {
        return categoryRepository.findAll()
    }

    @Throws(DataNotFoundException::class)
    fun findById(id: CategoryId): Category {
        try {
            return categoryRepository.findBy(id)
        } catch (e: DataNotFoundException) {
            // TODO: 挙動要確認
            throw e
        }

    }

    fun save(category: Category) {
        categoryRepository.save(category)
    }

    fun delete(id: CategoryId) {
        categoryRepository.delete(id)
    }
}