package com.example.shosekikun.repositoryImpl

import com.example.shosekikun.common.DataNotFoundException
import com.example.shosekikun.entity.*
import com.example.shosekikun.mapper.CategoryMapper
import com.example.shosekikun.repository.BookRepository
import com.example.shosekikun.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(
): BookRepository {
    override fun findAll(): List<Book> {
        return  emptyList()
//        return  categoryMapper.findAll().map {
//            Book(
//                id = BookId(it.categoryId ?: throw DataNotFoundException("idが見つかりませんでした。")),
//                title = it.name ?: "",
//            )
//        }
    }

    override fun findBy(id: BookId): Book {
        return  Book.EMPTY
    }

    override fun findBy(id: AuthorId): List<Book> {
        return emptyList()
    }

    override fun findBy(id: CategoryId): List<Book> {
        return emptyList()
    }

    override fun save(id: Book) {
        return
    }

    override fun delete(id: BookId) {
        return
    }
}