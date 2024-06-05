package com.example.shosekikun.repositoryImpl

import com.example.shosekikun.common.DateUtil
import com.example.shosekikun.entity.Author
import com.example.shosekikun.entity.AuthorId
import com.example.shosekikun.mapper.AuthorMapper
import com.example.shosekikun.repository.AuthorRepository
import org.springframework.stereotype.Repository

@Repository
class AuthorRepositoryImpl(
    private val authorMapper: AuthorMapper,
) : AuthorRepository {
    override fun findAll(): List<Author> {
        return authorMapper.findAll().map {
            Author(
                id = AuthorId(it.id!!),
                name = it.name ?: "",
                createDate = it.createdAt,
                updateDate = it.modifiedAt,
            )
        }
    }

    override fun findBy(id: AuthorId): Author {
        return authorMapper.findBy(id.asInt()).let {
            Author(
                id = AuthorId(it.id!!),
                name = it.name ?: "",
                createDate = it.createdAt,
                updateDate = it.modifiedAt,
            )
        }
    }

    override fun insert(authorName: String) {
        authorMapper.insert(authorName, DateUtil.getNow())
    }

    override fun update(author: Author) {
        println("impl: $author")
        authorMapper.update(author.id.asInt(), author.name, DateUtil.getNow())
    }

    override fun delete(authorId: AuthorId) {
        authorMapper.delete(authorId.asInt())
    }

}