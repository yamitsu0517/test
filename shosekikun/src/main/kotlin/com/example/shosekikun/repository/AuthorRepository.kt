package com.example.shosekikun.repository

import com.example.shosekikun.entity.Author
import com.example.shosekikun.entity.AuthorId

interface AuthorRepository {
    fun findAll(): List<Author>

    fun findBy(id: AuthorId): Author

    fun insert(authorName: String)

    fun update(author: Author)

    fun delete(authorId: AuthorId)
}