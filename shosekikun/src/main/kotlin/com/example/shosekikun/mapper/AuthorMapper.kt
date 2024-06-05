package com.example.shosekikun.mapper

import com.example.shosekikun.dto.AuthorDto
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import java.time.LocalDateTime

@Mapper
interface AuthorMapper {
    @Select(
        """
        SELECT
            *
        FROM
            AUTHORS
    """
    )
    fun findAll(): List<AuthorDto>

    @Select(
        """
        SELECT
            *
        FROM
            AUTHORS
        WHERE
            id = #{id}
    """
    )
    fun findBy(id: Int): AuthorDto

    @Insert(
        """
        INSERT INTO
            AUTHORS
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
            AUTHORS
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
            AUTHORS
        WHERE
            id = #{id}
    """
    )
    fun delete(id: Int)

}