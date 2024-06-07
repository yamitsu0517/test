package com.example.shosekikun.usecase

import com.example.shosekikun.entity.Author
import com.example.shosekikun.entity.AuthorId
import com.example.shosekikun.form.AuthorForm
import com.example.shosekikun.repository.AuthorRepository
import org.springframework.stereotype.Service

@Service
class AuthorUsecase(
    private val authorRepository: AuthorRepository,
) {
    fun findAll(): List<Author> {
        return authorRepository.findAll()
    }

    fun findById(id: AuthorId): Author {
        return authorRepository.findBy(id)
    }

    fun create(author: AuthorForm) {
        authorRepository.insert(author.name!!)
    }

    fun save(author: Author) {
        authorRepository.update(author)
    }

    fun delete(id: AuthorId) {
        authorRepository.delete(id)
    }
}
