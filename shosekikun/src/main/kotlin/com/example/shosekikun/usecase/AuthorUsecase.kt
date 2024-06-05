package com.example.shosekikun.usecase

import com.example.shosekikun.common.DataNotFoundException
import com.example.shosekikun.entity.Author
import com.example.shosekikun.entity.AuthorId
import com.example.shosekikun.form.AuthorForm
import com.example.shosekikun.repository.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorUsecase(
    @Autowired
    private val authorRepository: AuthorRepository,
) {
    fun getAllAuthors(): List<Author> {
        return authorRepository.findAll()
    }

    @Throws(DataNotFoundException::class)
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