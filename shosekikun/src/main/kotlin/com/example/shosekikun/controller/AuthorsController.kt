package com.example.shosekikun.controller

import com.example.shosekikun.common.Const
import com.example.shosekikun.common.DataNotFoundException
import com.example.shosekikun.common.FlashData
import com.example.shosekikun.entity.Author
import com.example.shosekikun.entity.AuthorId
import com.example.shosekikun.entity.Book
import com.example.shosekikun.form.AuthorForm
import com.example.shosekikun.presenter.author.AuthorPresenter
import com.example.shosekikun.usecase.AuthorUsecase
import com.example.shosekikun.usecase.BookUsecase
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
@RequestMapping("/authors")
class AuthorsController(
    private val authorUsecase: AuthorUsecase,
    private val bookUsecase: BookUsecase,
) {
    @GetMapping
    fun list(model: Model): String {
        val authors = authorUsecase.findAll()
        model.addAttribute(
            "authorsPresenter",
            AuthorPresenter(
                authors = authors
            )
        )
        return "authors/list"
    }

    @GetMapping("/create")
    fun add(model: Model): String {
        model.addAttribute("author", AuthorForm())
        return "authors/create"
    }

    @PostMapping("/create")
    fun create(
        @Validated @ModelAttribute author: AuthorForm,
        result: BindingResult,
        model: Model,
        ra: RedirectAttributes
    ): String {
        var flash: FlashData
        try {
            if (author.name.isNullOrEmpty()) {
                model.addAttribute("messages", listOf(Const.AUTHOR_FORM_ERROR_MESSAGE1))
                model.addAttribute("author", AuthorForm())
                return "authors/create"
            }
            // 登録処理
            authorUsecase.create(author)
            flash = FlashData().success(Const.AUTHOR_REGIST_SUCCESS)
        } catch (e: Exception) {
            flash = FlashData().danger(Const.COMMON_ERROR_MESSAGE)
        }
        ra.addFlashAttribute("flash", flash)
        return "redirect:/authors"
    }

    @GetMapping("/edit/{id}")
    fun edit(@PathVariable id: Int, model: Model): String {
        try {
            model.addAttribute(
                "author",
                authorUsecase.findById(AuthorId(id))
            )
        } catch (e: DataNotFoundException) {
            return "redirect:/authors"
        }
        return "authors/edit"
    }

    @PostMapping("/edit")
    fun edit(
        @Validated @ModelAttribute author: AuthorForm,
        result: BindingResult,
        model: Model,
        ra: RedirectAttributes
    ): String {
        var flash: FlashData
        try {
            if (author.id == null || author.name.isNullOrEmpty()) {
                model.addAttribute("messages", listOf(Const.AUTHOR_FORM_ERROR_MESSAGE1))
                model.addAttribute("author", author)
                return "authors/edit"
            }
            println("id: ${author.id}")
            println("name ${author.name}")
            println("authorId: ${AuthorId(author.id)}")
            authorUsecase.save(
                author = Author(
                    id = AuthorId(author.id),
                    name = author.name,
                )
            )
            flash = FlashData().success(Const.AUTHOR_EDIT_SUCCESS)
        } catch (e: Exception) {
            flash = FlashData().danger(Const.COMMON_ERROR_MESSAGE)
        }
        ra.addFlashAttribute("flash", flash)
        return "redirect:/authors"
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Int, ra: RedirectAttributes): String {
        var flash: FlashData
        try {
            val books: List<Book> = bookUsecase.findByAuthorId(AuthorId(id ?: 0))
            if (books.isEmpty()) {
                authorUsecase.findById(AuthorId(id))
                authorUsecase.delete(AuthorId(id))
                flash = FlashData().success(Const.AUTHOR_DELETE_SUCCESS)
            } else {
                flash = FlashData().danger(Const.AUTHOR_DELETE_EXISTS_BOOK)
            }
        } catch (e: DataNotFoundException) {
            flash = FlashData().danger(Const.COMMON_DELETE_NOT_FOUND)
        } catch (e: Exception) {
            flash = FlashData().danger(Const.COMMON_ERROR_MESSAGE)
        }
        ra.addFlashAttribute("flash", flash)
        return "redirect:/authors"
    }
}
