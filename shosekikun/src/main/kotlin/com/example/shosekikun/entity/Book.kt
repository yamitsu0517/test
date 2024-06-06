package com.example.shosekikun.entity

import com.example.shosekikun.common.DateUtil
import java.time.LocalDateTime

data class Book(
    val id: BookId,
    val title: String,
    val price: Int,
    val author: Author,
    val category: Category,
    private val createDate: LocalDateTime? = null,
    private val updateDate: LocalDateTime? = null,
) {
    val createDateFormated: String = DateUtil.dateFormat(createDate ?: DateUtil.getNow())
    val updateDateFormated: String = DateUtil.dateFormat(updateDate ?: DateUtil.getNow())
}
