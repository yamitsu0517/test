package com.example.shosekikun.usecase

import com.example.shosekikun.common.DataNotFoundException
import com.example.shosekikun.entity.Author
import com.example.shosekikun.entity.AuthorId
import com.example.shosekikun.repository.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorUsecase(
    @Autowired
    private val authorRepository: AuthorRepository,
) {
    fun findAll(): List<Author> {
        return authorRepository.findAll()
    }

    @Throws(DataNotFoundException::class)
    fun findById(id: AuthorId): Author {
        return authorRepository.findBy(id)
    }

    fun save(author: Author) {
        authorRepository.save(author)
    }

    fun delete(id: AuthorId) {
        authorRepository.delete(id)
    }
}