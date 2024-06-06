package com.example.shosekikun.repositoryImpl

import com.example.shosekikun.common.DateUtil
import com.example.shosekikun.entity.Category
import com.example.shosekikun.entity.CategoryId
import com.example.shosekikun.mapper.CategoryMapper
import com.example.shosekikun.repository.CategoryRepository
import org.springframework.stereotype.Repository

@Repository
class CategoryRepositoryImpl(
    private val categoryMapper: CategoryMapper,
) : CategoryRepository {
    override fun findAll(): List<Category> {
        return categoryMapper.findAll().map {
            Category(
                // id が取得できないことはないため、ヌルポにはならない
                id = CategoryId(it.id!!),
                name = it.name ?: "",
                createDate = it.createdAt,
                updateDate = it.modifiedAt,
            )
        }
    }

    override fun findBy(id: CategoryId): Category {
        return categoryMapper.findBy(id.asInt()).let {
            Category(
                // id が取得できないことはないため、ヌルポにはならない
                id = CategoryId(it.id!!),
                name = it.name ?: "",
                createDate = it.createdAt,
                updateDate = it.modifiedAt
            )
        }
    }

    override fun insert(categoryName: String) {
        categoryMapper.insert(
            name = categoryName,
            now = DateUtil.getNow(),
        )
    }

    override fun save(category: Category) {
        categoryMapper.update(
            id = category.id.asInt(),
            name = category.name,
            now = DateUtil.getNow(),
        )
    }

    override fun delete(categoryId: CategoryId) {
        categoryMapper.delete(
            id = categoryId.asInt(),
        )
    }
}
