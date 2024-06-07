package com.example.shosekikun.usecase

import com.example.shosekikun.entity.AuthorId
import com.example.shosekikun.entity.Book
import com.example.shosekikun.entity.BookId
import com.example.shosekikun.entity.CategoryId
import com.example.shosekikun.form.BookForm
import com.example.shosekikun.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookUsecase(
    private val bookRepository: BookRepository,
) {

    fun findAll(): List<Book> {
        return bookRepository.findAll()
    }

    fun findById(id: BookId): Book {
        return bookRepository.findBy(id)
    }

    fun findByAuthorId(authorId: AuthorId): List<Book> {
        return bookRepository.findBy(authorId)
    }

    fun findByCategoryId(categoryId: CategoryId): List<Book> {
        return bookRepository.findBy(categoryId)
    }

    fun create(book: BookForm) {
        bookRepository.insert(book)
    }

    fun save(book: BookForm) {
        bookRepository.save(book)
    }

    fun delete(id: BookId) {
        bookRepository.delete(id)
    }
}
