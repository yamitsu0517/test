package com.example.shosekikun.controller

import com.example.shosekikun.entity.Category
import com.example.shosekikun.usecase.BookUsecase
import com.example.shosekikun.usecase.CategoryUsecase
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("categories")
class CategoriesController(
    private val categoryService: CategoryUsecase,
    private val bookUsecase: BookUsecase,
) {
    @GetMapping(value = ["/", ""])
    fun list(model: Model): String {
        model.addAttribute("categories", categoryService.findAll())
        return "categories/list"
    }

    @GetMapping("/create")
    fun add(@ModelAttribute category: Category?, model: Model): String {
        model.addAttribute("isNew", true)
        return "categories/form"
    }

//    @PostMapping("/process")
//    fun process(
//        @Validated @ModelAttribute category: Category?,
//        result: BindingResult,
//        model: Model,
//        ra: RedirectAttributes
//    ): String {
//        var flash: FlashData
//        try {
//            if (result.hasErrors()) {
//                model.addAttribute("isNew", category?.id == null)
//                return "categories/form"
//            }
//            val type = if ((category?.id == null)) "追加" else "編集"
//            categoryService.save(category)
//            flash = FlashData().success("カテゴリの" + type + "が完了しました")
//        } catch (e: Exception) {
//            flash = FlashData().danger("処理中にエラーが発生しました")
//        }
//        ra.addFlashAttribute("flash", flash)
//        return "redirect:/categories"
//    }
//
//    @GetMapping("/edit/{id}")
//    fun edit(@PathVariable id: Int?, model: Model): String {
//        model.addAttribute("isNew", false)
//        try {
//            model.addAttribute("category", categoryService.findById(id))
//        } catch (e: DataNotFoundException) {
//            return "redirect:/authors"
//        }
//        return "categories/form"
//    }
//
//    @GetMapping("/delete/{id}")
//    fun delete(@PathVariable id: Int?, ra: RedirectAttributes): String {
//        var flash: FlashData
//        try {
//            val books: List<Book> = bookService.findByAuthorId(id)
//            if (books.isEmpty()) {
//                categoryService.findById(id)
//                categoryService.delete(id)
//                flash = FlashData().success("カテゴリの削除が完了しました")
//            } else {
//                flash = FlashData().danger("書籍に登録されているカテゴリは削除できません")
//            }
//        } catch (e: DataNotFoundException) {
//            flash = FlashData().danger("該当データがありません")
//        } catch (e: Exception) {
//            flash = FlashData().danger("処理中にエラーが発生しました")
//        }
//        ra.addFlashAttribute("flash", flash)
//        return "redirect:/categories"
//    }
}