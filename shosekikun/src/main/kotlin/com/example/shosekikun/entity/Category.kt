package com.example.shosekikun.entity

import com.example.shosekikun.common.DateUtil
import java.time.LocalDateTime

data class Category(
    val id: CategoryId,
    val name: String,
    private val createDate: LocalDateTime? = null,
    private val updateDate: LocalDateTime? = null,
) {
    val createDateFormated: String = DateUtil.dateFormat(createDate ?: DateUtil.getNow())
    val updateDateFormated: String = DateUtil.dateFormat(updateDate ?: DateUtil.getNow())
}
