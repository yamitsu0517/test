package com.example.shosekikun.controller

import com.example.shosekikun.common.DataNotFoundException
import com.example.shosekikun.common.FlashData
import com.example.shosekikun.entity.Author
import com.example.shosekikun.entity.AuthorId
import com.example.shosekikun.entity.Book
import com.example.shosekikun.usecase.AuthorUsecase
import com.example.shosekikun.usecase.BookUsecase
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes


@Controller
@RequestMapping("/authors")
class AuthorsController(
    private val authorService: AuthorUsecase,
    private val bookUsecase: BookUsecase,
) {


    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("authors", authorService.findAll())
        return "authors/list"
    }

    @GetMapping("/create")
    fun add(@ModelAttribute author: Author?, model: Model): String {
        model.addAttribute("isNew", true)
        return "authors/form"
    }

    @PostMapping("/process")
    fun process(
        @Validated @ModelAttribute author: Author?,
        result: BindingResult,
        model: Model,
        ra: RedirectAttributes
    ): String {
        var flash: FlashData
        try {
            if (result.hasErrors()) {
                model.addAttribute("isNew", author?.id == null)
                return "authors/form"
            }
            val type = if ((author?.id == null)) "追加" else "編集"
            authorService.save(author ?: throw DataNotFoundException("データがありませんでした。"))
            flash = FlashData().success("著者の" + type + "が完了しました")
        } catch (e: Exception) {
            flash = FlashData().danger("処理中にエラーが発生しました")
        }
        ra.addFlashAttribute("flash", flash)
        return "redirect:/authors"
    }

    @GetMapping("/edit/{id}")
    fun edit(@PathVariable id: Int?, model: Model): String {
        model.addAttribute("isNew", false)
        try {
            model.addAttribute("author", authorService.findById(AuthorId(id ?: 0)))
        } catch (e: DataNotFoundException) {
            return "redirect:/authors"
        }
        return "authors/form"
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Int?, ra: RedirectAttributes): String {
        var flash: FlashData
        try {
            val books: List<Book> = bookUsecase.findByAuthorId(AuthorId(id ?: 0))
            if (books.isEmpty()) {
                authorService.findById(AuthorId(id ?: 0))
                authorService.delete(AuthorId(id ?: 0))
                flash = FlashData().success("著者の削除が完了しました")
            } else {
                flash = FlashData().danger("書籍に登録されている著者は削除できません")
            }
        } catch (e: DataNotFoundException) {
            flash = FlashData().danger("該当データがありません")
        } catch (e: Exception) {
            flash = FlashData().danger("処理中にエラーが発生しました")
        }
        ra.addFlashAttribute("flash", flash)
        return "redirect:/authors"
    }
}