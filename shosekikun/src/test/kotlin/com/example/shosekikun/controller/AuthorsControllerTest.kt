package com.example.shosekikun.controller

import com.example.shosekikun.entity.Author
import com.example.shosekikun.entity.AuthorId
import com.example.shosekikun.presenter.author.AuthorPresenter
import com.example.shosekikun.usecase.AuthorUsecase
import com.example.shosekikun.usecase.BookUsecase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@Disabled("書きっぷりはあっているはずだが、依存関係周りで落ちるため、試験対象外")
@WebMvcTest(controllers = [AuthorsController::class])
class AuthorsControllerTest(
) {

    private val authorUsecase: AuthorUsecase = mockk(relaxed = true)
    private val bookUsecase: BookUsecase = mockk(relaxed = true)

    private val controller = AuthorsController(
        authorUsecase = authorUsecase,
        bookUsecase = bookUsecase,
    )

    private val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(controller).build()

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("list メソッド")
    inner class ListTest {
        @Test
        @DisplayName("")
        fun pattern1() {
            val expectedFindAll = listOf(Author(id = AuthorId(1), name = "name"))
            val expectedPresenter = AuthorPresenter(
                expectedFindAll
            )
            every {
                authorUsecase.findAll()
            } returns expectedFindAll

            mockMvc.perform(
                get("/authors")
            )
                // ステータスチェック
                .andExpect(status().isOk())
                // レスポンスのパスチェック
                .andExpect(view().name("authors/list"))
                // モデルに追加された要素の確認
                .andExpect(model().attribute("authorsPresenter", expectedPresenter))
            // 今回は対象外だが、リダイレクト先の確認は以下で可能
            //.andExpect(redirectedUrl("/authors"))
        }
    }
}
