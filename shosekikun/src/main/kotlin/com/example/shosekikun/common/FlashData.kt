package com.example.shosekikun.common

import lombok.Data

@Data
class FlashData {
    private val SUCCESS: String = "success"
    private val DANGER: String = "danger"
    private val WARNING: String = "warning"
    private val INFO: String = "info"

    // null 非許容のためJava板とは変える
    private var key: String = ""
    private var message: String = ""

    fun getKey() = key
    fun getMessage() = message

    fun FlashData() {
        this.key = INFO
    }

    fun success(message: String): FlashData {
        this.key = SUCCESS
        this.message = message
        return this
    }

    fun danger(message: String): FlashData {
        this.key = DANGER
        this.message = message
        return this
    }

    fun warning(message: String): FlashData {
        this.key = WARNING
        this.message = message
        return this
    }

    fun info(message: String): FlashData {
        this.key = INFO
        this.message = message
        return this
    }
}