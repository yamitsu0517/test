package com.example.shosekikun.controller

import com.example.shosekikun.common.DataNotFoundException
import com.example.shosekikun.common.FlashData
import com.example.shosekikun.entity.Author
import com.example.shosekikun.entity.BookId
import com.example.shosekikun.entity.Category
import com.example.shosekikun.form.BookForm
import com.example.shosekikun.presenter.book.BookCreatePresenter
import com.example.shosekikun.presenter.book.BookEditPresenter
import com.example.shosekikun.usecase.AuthorUsecase
import com.example.shosekikun.usecase.BookUsecase
import com.example.shosekikun.usecase.CategoryUsecase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/books")
class BooksController(
    private val bookUsecase: BookUsecase,
    private val categoryUsecase: CategoryUsecase,
    private val authorUsecase: AuthorUsecase,
) {
    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("books", bookUsecase.findAll())
        return "books/list"
    }

    @GetMapping("/create")
    fun create(model: Model): String {
        val categories: List<Category> = categoryUsecase.findAll()
        val authors: List<Author> = authorUsecase.findAll()

        addCreateModel(
            model = model,
            categories = categories,
            authors = authors,
            book = BookForm(),
        )
        return "books/create"
    }

    @PostMapping("/create")
    fun create(
        @Validated @ModelAttribute book: BookForm,
        result: BindingResult,
        model: Model,
        ra: RedirectAttributes
    ): String {
        var flash: FlashData
        try {
            val messages = getErrorMessage(book)
            if (messages.isNotEmpty()) {
                val categories: List<Category> = categoryUsecase.findAll()
                val authors: List<Author> = authorUsecase.findAll()
                addCreateModel(
                    model = model,
                    categories = categories,
                    authors = authors,
                    messages = messages,
                    book = book
                )
                return "books/create"
            }
            bookUsecase.create(book)
            flash = FlashData().success("書籍の追加が完了しました")
        } catch (e: Exception) {
            flash = FlashData().danger("処理中にエラーが発生しました")
        }
        ra.addFlashAttribute("flash", flash)
        return "redirect:/books"
    }

    @GetMapping("/edit/{id}")
    fun edit(@PathVariable id: Int, model: Model): String {
        try {
            addEditModel(model, id)
        } catch (e: DataNotFoundException) {
            return "redirect:/books"
        }
        return "books/edit"
    }

    @PostMapping("/edit")
    fun edit(
        @Validated @ModelAttribute book: BookForm,
        result: BindingResult,
        model: Model,
        ra: RedirectAttributes
    ): String {
        var flash: FlashData
        try {
            val messages = getErrorMessage(book)
            if (messages.isNotEmpty()) {
                addEditModel(model, book.id!!)
                return "books/edit/" + book.id
            }
            bookUsecase.save(book)
            flash = FlashData().success("書籍の更新が完了しました")
        } catch (e: Exception) {
            throw e
            flash = FlashData().danger("処理中にエラーが発生しました")
        }
        ra.addFlashAttribute("flash", flash)
        return "redirect:/books"
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Int?, ra: RedirectAttributes): String {
        var flash: FlashData
        try {
            id ?: throw DataNotFoundException("IDがありません。")
            bookUsecase.findById(BookId(id))
            bookUsecase.delete(BookId(id))
            flash = FlashData().success("書籍の削除が完了しました")
            ra.addFlashAttribute("flash", flash)
        } catch (e: DataNotFoundException) {
            flash = FlashData().danger("該当データがありません")
        } catch (e: Exception) {
            flash = FlashData().danger("処理中にエラーが発生しました")
        }
        ra.addFlashAttribute("flash", flash)
        return "redirect:/books"
    }

    private fun addCreateModel(
        model: Model,
        book: BookForm,
        categories: List<Category>,
        authors: List<Author>,
        messages: List<String> = emptyList(),
    ) {
        model.addAttribute(
            "bookCreatePresenter", BookCreatePresenter(
                categories = categories,
                authors = authors
            )
        )
        model.addAttribute("book", book)
        model.addAttribute("messages", messages)
    }

    private fun addEditModel(model: Model, bookId: Int) {
        val book = bookUsecase.findById(BookId(bookId))
        val categories: List<Category> = categoryUsecase.findAll()
        val authors: List<Author> = authorUsecase.findAll()
        model.addAttribute(
            "bookEditPresenter", BookEditPresenter(
                authors = authors,
                categories = categories,
            )
        )
        model.addAttribute(
            "bookForm", BookForm(
                id = book.id.asInt(),
                title = book.title,
                price = book.price,
                authorId = book.author.id.asInt(),
                categoryId = book.category.id.asInt(),
            )
        )
    }

    private fun getErrorMessage(form: BookForm): List<String> {
        val messages = mutableListOf<String>()
        if (form.price == null || form.price < 1) messages.add("価格を入力してください。")
        if (form.title.isNullOrEmpty()) messages.add("タイトルを入力してください。")
        if (form.categoryId == null) messages.add("カテゴリを指定してください。")
        if (form.authorId == null) messages.add("著者を指定してください。")

        return messages
    }
}
