package com.example.shosekikun.mapper

import com.example.shosekikun.dto.BookDto
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import java.time.LocalDateTime

@Mapper
interface BookMapper {
    @Select(
        """
        SELECT
              b.id id
            , b.title title
            , b.price price
            , a.id authorId
            , a.name authorName
            , c.id categoryId
            , c.name categoryName
            , b.created_at createdAt
            , b.modified_at modifiedAt
        FROM
              BOOKS b
            , AUTHORS a
            , CATEGORIES c
        WHERE
            b.author_id = a.id
            AND b.category_id = c.id
    """
    )
    fun findAll(): List<BookDto>

    @Select(
        """
        <script>
        SELECT
              b.id id
            , b.title title
            , b.price price
            , a.id authorId
            , a.name authorName
            , c.id categoryId
            , c.name categoryName
            , b.created_at createdAt
            , b.modified_at modifiedAt
        FROM
              BOOKS b
            , AUTHORS a
            , CATEGORIES c
        WHERE
            b.author_id = a.id
            AND b.category_id = c.id
            
            <if test="bookId != null and bookId != ''">
                AND b.id = #{bookId}
            </if>
            <if test="authorId != null and authorId != ''">
                AND a.id = #{authorId}
            </if>
            <if test="categoryId != null and categoryId != ''">
                AND c.id = #{categoryId}
            </if>
        </script>
    """
    )
    fun findBy(
        @Param("bookId") bookId: Int? = null,
        @Param("authorId") authorId: Int? = null,
        @Param("categoryId") categoryId: Int? = null
    ): List<BookDto>

    @Insert(
        """
        INSERT INTO
            BOOKS   
        (
              title
            , PRICE
            , AUTHOR_ID
            , CATEGORY_ID
            , CREATED_AT
            , MODIFIED_AT
        )
        VALUES (
              #{title}
            , #{price}
            , #{authorId}
            , #{categoryId}
            , #{now}
            , #{now}
        )
    """
    )
    fun insert(
        @Param("title") title: String,
        @Param("price") price: Int,
        @Param("authorId") authorId: Int,
        @Param("categoryId") categoryId: Int,
        @Param("now") now: LocalDateTime,
    )

    @Update(
        """
        UPDATE
            BOOKS
        SET
              title = #{title}
            , price = #{price}
            , author_id = #{authorId}
            , category_id = #{categoryId}
            , modified_at = #{now}
        WHERE
            id = #{id}
    """
    )
    fun update(
        @Param("id") id: Int,
        @Param("title") title: String,
        @Param("price") price: Int,
        @Param("authorId") authorId: Int,
        @Param("categoryId") categoryId: Int,
        @Param("now") now: LocalDateTime,
    )

    @Delete(
        """
        DELETE FROM
            BOOKS
        WHERE
            id = #{id}
    """
    )
    fun delete(id: Int)

}
