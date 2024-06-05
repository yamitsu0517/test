package com.example.shosekikun.repositoryImpl

import com.example.shosekikun.entity.AuthorId
import com.example.shosekikun.entity.Book
import com.example.shosekikun.entity.BookId
import com.example.shosekikun.entity.CategoryId
import com.example.shosekikun.repository.BookRepository
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