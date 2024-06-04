package com.example.shosekikun.repository

import com.example.shosekikun.entity.Author
import com.example.shosekikun.entity.AuthorId
import com.example.shosekikun.entity.Category
import com.example.shosekikun.entity.CategoryId
import org.springframework.stereotype.Component

interface AuthorRepository {
    fun findAll(): List<Author>

    fun findBy(id: AuthorId): Author

    fun save(author: Author)

    fun delete(authorId: AuthorId)
}