package com.example.shosekikun.controller

import com.example.shosekikun.common.Const
import com.example.shosekikun.common.DataNotFoundException
import com.example.shosekikun.common.FlashData
import com.example.shosekikun.entity.Book
import com.example.shosekikun.entity.Category
import com.example.shosekikun.entity.CategoryId
import com.example.shosekikun.form.CategoryForm
import com.example.shosekikun.presenter.category.CategoryPresenter
import com.example.shosekikun.usecase.BookUsecase
import com.example.shosekikun.usecase.CategoryUsecase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("categories")
class CategoriesController(
    private val categoryService: CategoryUsecase,
    private val bookUsecase: BookUsecase,
) {
    @GetMapping(value = ["/", ""])
    fun list(model: Model): String {
        val categories: List<Category> = categoryService.findAll()
        model.addAttribute(
            "categoryPresenter",
            CategoryPresenter(
                categories = categories
            )
        )
        return "categories/list"
    }

    @GetMapping("/create")
    fun create(
//        @ModelAttribute category: CategoryForm,
        model: Model,
    ): String {
        model.addAttribute("category", CategoryForm())
        return "categories/create"
    }

    @PostMapping("/create")
    fun create(
        @ModelAttribute category: CategoryForm,
//        @Valid @NotEmpty @RequestBody categoryName: String?,
        result: BindingResult,
        model: Model,
        ra: RedirectAttributes
    ): String {
        var flash: FlashData

        try {
            if (category.name.isNullOrEmpty()) {
                model.addAttribute("messages", listOf(Const.CATEGORY_FORM_ERROR_MESSAGE1))
                model.addAttribute("category", CategoryForm())
                return "categories/create"
            }
            categoryService.create(category.name)
            flash = FlashData().success(Const.CATEGORY_REGIST_SUCCESS)
        } catch (e: Exception) {
            flash = FlashData().danger(Const.CATEGORY_REGIST_FAILURE)
        }
        ra.addFlashAttribute("flash", flash)
        return "redirect:/categories"
    }

    @GetMapping("/edit/{id}")
    fun edit(@PathVariable id: Int, model: Model): String {
        try {
            model.addAttribute(
                "category",
                categoryService.getCategoryBy(CategoryId(id))
            )
        } catch (e: DataNotFoundException) {
            return "redirect:/authors"
        }
        return "categories/edit"
    }

    @PostMapping("/edit")
    fun edit(
        @ModelAttribute category: CategoryForm,
//        @Valid @NotEmpty @RequestBody categoryName: String?,
        result: BindingResult,
        model: Model,
        ra: RedirectAttributes
    ): String {
        var flash: FlashData

        try {
            if (category.id == null || category.name.isNullOrEmpty()) {
                model.addAttribute("messages", listOf(Const.CATEGORY_FORM_ERROR_MESSAGE1))
                model.addAttribute("category", category)
                return "categories/edit"
            }
            categoryService.save(
                Category(
                    id = CategoryId(category.id),
                    name = category.name,
                )
            )
            flash = FlashData().success(Const.CATEGORY_UPDATE_SUCCESS)
        } catch (e: Exception) {
            flash = FlashData().danger(Const.CATEGORY_UPDATE_FAILURE)
        }
        ra.addFlashAttribute("flash", flash)
        return "redirect:/categories"
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Int, ra: RedirectAttributes): String {
        var flash: FlashData
        try {
            val books: List<Book> =
                bookUsecase.findByCategoryId(CategoryId(id))
            if (books.isEmpty()) {
                categoryService.getCategoryBy(CategoryId(id))
                categoryService.delete(CategoryId(id))
                flash = FlashData().success(Const.CATEGORY_DELETE_SUCCESS)
            } else {
                flash = FlashData().danger(Const.CATEGORY_DELETE_BOOK_EXISTS)
            }
        } catch (e: DataNotFoundException) {
            flash = FlashData().danger(Const.CATEGORY_DELETE_NOT_FOUND)
        } catch (e: Exception) {
            flash = FlashData().danger(Const.CATEGORY_DELETE_FAILURE)
        }
        ra.addFlashAttribute("flash", flash)
        return "redirect:/categories"
    }
}