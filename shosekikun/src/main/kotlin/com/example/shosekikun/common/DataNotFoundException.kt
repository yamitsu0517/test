package com.example.shosekikun.common

class DataNotFoundException : Exception {
    constructor()

    constructor(msg: String?) : super(msg)

    constructor(th: Throwable?) : super(th)

    constructor(msg: String?, th: Throwable?) : super(msg, th)
}
