package com.example.shosekikun.repositoryImpl

import com.example.shosekikun.common.DataNotFoundException
import com.example.shosekikun.entity.Category
import com.example.shosekikun.entity.CategoryId
import com.example.shosekikun.mapper.CategoryMapper
import com.example.shosekikun.repository.CategoryRepository
import org.springframework.stereotype.Repository

@Repository
class CategoryRepositoryImpl(
    private val categoryMapper: CategoryMapper,
): CategoryRepository {
    override fun findAll(): List<Category> {
        return  categoryMapper.findAll().map {
            Category(
                id = CategoryId(it.categoryId ?: throw DataNotFoundException("idが見つかりませんでした。")),
                name = it.name ?: "",
                createDate = it.createDate,
                updateDate = it.updateDate,
            )
        }
    }

    override fun findBy(id: CategoryId): Category {
        return Category.EMPTY
    }

    override fun save(category: Category) {
        return
    }

    override fun delete(categoryId: CategoryId) {
        return
    }
}