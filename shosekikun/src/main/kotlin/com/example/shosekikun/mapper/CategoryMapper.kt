package com.example.shosekikun.mapper

import com.example.shosekikun.dto.CategoryDto
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import java.time.LocalDateTime

@Mapper
interface CategoryMapper {
    @Select(
        """
        SELECT
            *
        FROM
            CATEGORIES
    """
    )
    fun findAll(): List<CategoryDto>

    @Select(
        """
        SELECT
            *
        FROM
            CATEGORIES
        WHERE
            id = #{id}
    """
    )
    fun findBy(id: Int): CategoryDto

    @Insert(
        """
        INSERT INTO
            CATEGORIES
        (
              NAME
            , CREATED_AT
            , MODIFIED_AT
        )
        VALUES (
              #{name}
            , #{now}
            , #{now}
        )
    """
    )
    fun insert(name: String, now: LocalDateTime)

    @Update(
        """
        UPDATE
            CATEGORIES
        SET
              NAME = #{name}
            , modified_at = #{now}
        WHERE
            id = #{id}
    """
    )
    fun update(id: Int, name: String, now: LocalDateTime)

    @Delete(
        """
        DELETE FROM
            CATEGORIES
        WHERE
            id = #{id}
    """
    )
    fun delete(id: Int)
}
