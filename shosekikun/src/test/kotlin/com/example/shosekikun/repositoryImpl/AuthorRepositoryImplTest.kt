package com.example.shosekikun.repositoryImpl

import com.example.shosekikun.dto.AuthorDto
import com.example.shosekikun.entity.Author
import com.example.shosekikun.entity.AuthorId
import com.example.shosekikun.mapper.AuthorMapper
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
import org.mockito.Mock
import java.time.LocalDateTime

class AuthorRepositoryImplTest {
    @Mock
    private val authorMapper: AuthorMapper = mockk(relaxed = true)

    private val repository: AuthorRepositoryImpl = AuthorRepositoryImpl(
        authorMapper
    )

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("findAll メソッド")
    inner class FindAllTest {
        @ParameterizedTest
        @MethodSource("parameters")
        @DisplayName("パターン網羅")
        fun pattern(
            result: List<AuthorDto>,
            expected: List<Author>
        ) {

            every {
                authorMapper.findAll()
            } returns result

            val actual = repository.findAll()

            verify(exactly = 1) {
                authorMapper.findAll()
            }

            Assertions.assertEquals(expected, actual)
        }

        private fun parameters() = listOf(
            Arguments.of(
                listOf(
                    authorDtoFormat.copy(
                        id = 1,
                        name = "著者名"
                    ),
                ),
                listOf(
                    authorFormat.copy(
                        id = AuthorId(1),
                        name = "著者名",
                    )
                ),
            ),
            Arguments.of(
                listOf(
                    authorDtoFormat.copy(
                        id = 1,
                        name = "著者名1",
                    ),
                    authorDtoFormat.copy(
                        id = 2,
                        name = "著者名2",
                    ),
                ),
                listOf(
                    authorFormat.copy(
                        id = AuthorId(1),
                        name = "著者名1",
                    ),
                    authorFormat.copy(
                        id = AuthorId(2),
                        name = "著者名2",
                    )
                ),
            ),
            Arguments.of(
                emptyList<AuthorDto>(),
                emptyList<Author>(),
            )
        )
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("findBy メソッド")
    inner class FindByTest {

        @ParameterizedTest
        @MethodSource("parameters")
        @DisplayName("パターン網羅")
        fun pattern(
            id: Int,
            result: AuthorDto,
            expected: Author,
        ) {
            every { authorMapper.findBy(any()) } returns result

            val actual = repository.findBy(AuthorId(id))

            verify(exactly = 1) {
                authorMapper.findBy(id)
            }

            Assertions.assertEquals(expected, actual)
        }

        private fun parameters() = listOf(
            Arguments.of(
                1,
                authorDtoFormat.copy(
                    id = 1,
                    name = "著者名"
                ),
                authorFormat.copy(
                    id = AuthorId(1),
                    name = "著者名",
                )
            ),
            Arguments.of(
                3,
                authorDtoFormat.copy(
                    id = 3,
                    name = "著者名3"
                ),
                authorFormat.copy(
                    id = AuthorId(3),
                    name = "著者名3",
                )
            ),
        )
    }

    companion object {
        private val now: LocalDateTime = LocalDateTime.of(2024, 5, 1, 12, 0, 0)

        val authorFormat = Author(
            id = AuthorId(1),
            name = "著者名1",
            createDate = now,
            updateDate = now,
        )
        val authorDtoFormat = AuthorDto(
            id = 1,
            name = "著者名1",
            createdAt = now,
            modifiedAt = now,
        )
    }
}
