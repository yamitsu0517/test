package com.example.shosekikun.controller

import com.example.shosekikun.common.DataNotFoundException
import com.example.shosekikun.common.FlashData
import com.example.shosekikun.entity.Author
import com.example.shosekikun.entity.BookId
import com.example.shosekikun.entity.Category
import com.example.shosekikun.form.BookForm
import com.example.shosekikun.presenter.book.BookCreatePresenter
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

        model.addAttribute(
            "bookCreatePresenter", BookCreatePresenter(
                categories = categories,
                authors = authors
            )
        )
        model.addAttribute("book", BookForm())
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
            if (validate(book)) {
                model.addAttribute("isNew", book?.id == null)
                return "books/form"
            }
            val type = if ((book.id == null)) "追加" else "編集"
            bookUsecase.save(book)
            flash = FlashData().success("書籍の" + type + "が完了しました")
        } catch (e: Exception) {
            flash = FlashData().danger("処理中にエラーが発生しました")
        }
        ra.addFlashAttribute("flash", flash)
        return "redirect:/books"
    }

    @GetMapping("/edit/{id}")
    fun edit(@PathVariable id: Int, model: Model): String {
        model.addAttribute("isNew", false)
        try {
            model.addAttribute("book", bookUsecase.findById(BookId(id)))
        } catch (e: DataNotFoundException) {
            return "redirect:/books"
        }
        return "books/form"
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
            if (result.hasErrors()) {
                model.addAttribute("isNew", book?.id == null)
                return "books/form"
            }
            val type = if ((book?.id == null)) "追加" else "編集"
            bookUsecase.save(book)
            flash = FlashData().success("書籍の" + type + "が完了しました")
        } catch (e: Exception) {
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

    private fun validate(form: BookForm): Boolean {
        if (form.price == null || form.price < 1) return true
        if (form.title.isNullOrEmpty()) return true
        if (form.categoryId == null) return true
        if (form.authorId == null) return true

        return false
    }
}