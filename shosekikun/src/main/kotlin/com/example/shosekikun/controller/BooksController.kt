package com.example.shosekikun.controller

import com.example.shosekikun.common.DataNotFoundException
import com.example.shosekikun.common.FlashData
import com.example.shosekikun.entity.Book
import com.example.shosekikun.entity.BookId
import com.example.shosekikun.usecase.BookUsecase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/books")
class BooksController(
    private val bookUsecase: BookUsecase,
) {
    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("books", bookUsecase.findAll())
        return "books/list"
    }

    @GetMapping("/create")
    fun create(@ModelAttribute book: Book?, model: Model): String {
        model.addAttribute("isNew", true)
        return "books/form"
    }

    @PostMapping("/process")
    fun process(
        @Validated @ModelAttribute book: Book?,
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
            bookUsecase.save(book ?: throw  DataNotFoundException("登録データがありません。"))
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

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Int?, ra: RedirectAttributes): String {
        var flash: FlashData
        try {
            id ?: throw  DataNotFoundException("IDがありません。")
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
}