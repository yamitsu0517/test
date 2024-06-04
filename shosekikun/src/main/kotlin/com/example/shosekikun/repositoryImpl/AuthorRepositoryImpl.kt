package com.example.shosekikun.repositoryImpl

import com.example.shosekikun.common.DataNotFoundException
import com.example.shosekikun.entity.*
import com.example.shosekikun.mapper.CategoryMapper
import com.example.shosekikun.repository.AuthorRepository
import com.example.shosekikun.repository.BookRepository
import com.example.shosekikun.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
class AuthorRepositoryImpl(
): AuthorRepository {
    override fun findAll(): List<Author> {
        return  emptyList()
//        return  categoryMapper.findAll().map {
//            Book(
//                id = BookId(it.categoryId ?: throw DataNotFoundException("idが見つかりませんでした。")),
//                title = it.name ?: "",
//            )
//        }
    }

    override fun findBy(id: AuthorId): Author {
        return Author.EMPTY
    }

    override fun save(author: Author) {

    }

    override fun delete(authorId: AuthorId) {

    }

}