package com.example.shosekikun.mapper

import com.example.shosekikun.dao.CategoryDto
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Mapper
interface CategoryMapper {
    @Select("""
        SELECT
            *
        FROM
            CATEGORIES
        ;
    """)
    fun findAll(): List<CategoryDto>
}