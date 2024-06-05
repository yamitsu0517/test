package com.example.shosekikun.common

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateUtil {
    companion object {
        fun getNow(): LocalDateTime {
            return LocalDateTime.now()
        }
        
        fun dateFormat(dateTime: LocalDateTime): String {
            val dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
            return dateTime.format(dtf)
        }

    }

}