package com.example.shosekikun.usecase

import com.example.shosekikun.common.DataNotFoundException
import com.example.shosekikun.entity.AuthorId
import com.example.shosekikun.entity.Book
import com.example.shosekikun.entity.BookId
import com.example.shosekikun.entity.CategoryId
import com.example.shosekikun.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookUsecase(
    private val bookRepository: BookRepository,
) {

    fun findAll(): List<Book> {
        return bookRepository.findAll()
    }

    @Throws(DataNotFoundException::class)
    fun findById(id: BookId): Book {
        return bookRepository.findBy(id)
    }

    @Throws(DataNotFoundException::class)
    fun findByAuthorId(authorId: AuthorId): List<Book> {
        return bookRepository.findBy(authorId)
    }

    @Throws(DataNotFoundException::class)
    fun findByCategoryId(categoryId: CategoryId): List<Book> {
        return bookRepository.findBy(categoryId)
    }

    fun save(book: Book) {
        bookRepository.save(book)
    }

    fun delete(id: BookId) {
        bookRepository.delete(id)
    }
}