package com.example.shosekikun.repositoryImpl

import com.example.shosekikun.common.DateUtil
import com.example.shosekikun.entity.Author
import com.example.shosekikun.entity.AuthorId
import com.example.shosekikun.entity.Book
import com.example.shosekikun.entity.BookId
import com.example.shosekikun.entity.Category
import com.example.shosekikun.entity.CategoryId
import com.example.shosekikun.form.BookForm
import com.example.shosekikun.mapper.BookMapper
import com.example.shosekikun.repository.BookRepository
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(
    private val bookMapper: BookMapper,
) : BookRepository {
    override fun findAll(): List<Book> {
        return bookMapper.findAll().map {
            Book(
                // id が取得できないことはないため、ヌルポにはならない
                id = BookId(it.id!!),
                title = it.title ?: "",
                price = it.price ?: 0,
                author = Author(
                    id = AuthorId(it.authorId!!),
                    name = it.authorName ?: "",
                ),
                category = Category(
                    id = CategoryId(it.categoryId!!),
                    name = it.categoryName ?: "",
                ),
                createDate = it.createdAt,
                updateDate = it.modifiedAt
            )
        }
    }

    override fun findBy(id: BookId): Book {
        return bookMapper.findBy(bookId = id.asInt()).map {
            Book(
                // id が取得できないことはないため、ヌルポにはならない
                id = BookId(it.id!!),
                title = it.title ?: "",
                price = it.price ?: 0,
                author = Author(
                    id = AuthorId(it.authorId!!),
                    name = it.authorName ?: "",
                ),
                category = Category(
                    id = CategoryId(it.categoryId!!),
                    name = it.categoryName ?: "",
                ),
                createDate = it.createdAt,
                updateDate = it.modifiedAt
            )
        }[0]
    }

    override fun findBy(id: AuthorId): List<Book> {
        return bookMapper.findBy(authorId = id.asInt()).map {
            Book(
                // id が取得できないことはないため、ヌルポにはならない
                id = BookId(it.id!!),
                title = it.title ?: "",
                price = it.price ?: 0,
                author = Author(
                    id = AuthorId(it.authorId!!),
                    name = it.authorName ?: "",
                ),
                category = Category(
                    id = CategoryId(it.categoryId!!),
                    name = it.categoryName ?: "",
                ),
                createDate = it.createdAt,
                updateDate = it.modifiedAt
            )
        }
    }

    override fun findBy(id: CategoryId): List<Book> {
        return bookMapper.findBy(categoryId = id.asInt()).map {
            Book(
                // id が取得できないことはないため、ヌルポにはならない
                id = BookId(it.id!!),
                title = it.title ?: "",
                price = it.price ?: 0,
                author = Author(
                    id = AuthorId(it.authorId!!),
                    name = it.authorName ?: "",
                ),
                category = Category(
                    id = CategoryId(it.categoryId!!),
                    name = it.categoryName ?: "",
                ),
                createDate = it.createdAt,
                updateDate = it.modifiedAt
            )
        }
    }

    override fun insert(form: BookForm) {
        val now = DateUtil.getNow()
        bookMapper.insert(
            // null チェック済みのため、ヌルポにはならない。
            title = form.title!!,
            price = form.price!!,
            authorId = form.authorId!!,
            categoryId = form.categoryId!!,
            now = now,
        )
    }

    override fun save(form: BookForm) {
        bookMapper.update(
            // null チェック済みのため、ヌルポにはならない。
            id = form.id!!,
            title = form.title!!,
            price = form.price!!,
            authorId = form.authorId!!,
            categoryId = form.categoryId!!,
            now = DateUtil.getNow(),
        )
    }

    override fun delete(id: BookId) {
        bookMapper.delete(id.asInt())
    }
}
