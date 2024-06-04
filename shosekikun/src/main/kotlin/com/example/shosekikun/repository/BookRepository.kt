package com.example.shosekikun.repository

import com.example.shosekikun.entity.Book
import com.example.shosekikun.entity.BookId
import com.example.shosekikun.entity.AuthorId
import com.example.shosekikun.entity.CategoryId
import org.springframework.stereotype.Component

@Component
interface BookRepository {
    fun findAll(): List<Book>

    fun findBy(id: BookId): Book

    fun findBy(id: AuthorId): List<Book>

    fun findBy(id: CategoryId): List<Book>

    fun save(id: Book)

    fun delete(id: BookId)
}