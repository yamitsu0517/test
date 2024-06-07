package com.example.shosekikun.usecase

import com.example.shosekikun.entity.Author
import com.example.shosekikun.entity.AuthorId
import com.example.shosekikun.repository.AuthorRepository
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDateTime

class AuthorUsecaseTest {

    private val repository: AuthorRepository = mockk()

    private val usecase = AuthorUsecase(repository)

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }


    @Nested
    @DisplayName("findAll メソッド")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class FindAllTest {
        @ParameterizedTest
        @MethodSource("parameters")
        @DisplayName("パターン網羅")
        fun pattern(
            result: List<Author>,
            expected: List<Author>,
        ) {

            every {
                repository.findAll()
            } returns result

            val actual = usecase.findAll()

            verify(exactly = 1) {
                repository.findAll()
            }

            Assertions.assertEquals(expected, actual)
        }

        private fun parameters() = listOf(
            Arguments.of(
                listOf(
                    authorFormat,
                ),
                listOf(
                    authorFormat,
                ),
            ),
            Arguments.of(
                listOf(
                    authorFormat,
                    authorFormat.copy(
                        id = AuthorId(2),
                        name = "著者2"
                    ),
                ),
                listOf(
                    authorFormat,
                    authorFormat.copy(
                        id = AuthorId(2),
                        name = "著者2"
                    ),
                ),
            ),
            Arguments.of(
                emptyList<Author>(),
                emptyList<Author>(),
            ),
        )

    }

    companion object {
        private val now = LocalDateTime.of(2024, 5, 1, 12, 0, 0)
        val authorFormat = Author(
            id = AuthorId(1),
            name = "著者1",
            createDate = now,
            updateDate = now,
        )
    }
}
