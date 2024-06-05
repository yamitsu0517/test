package com.example.shosekikun.mapper

import com.example.shosekikun.dto.CategoryDto
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

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