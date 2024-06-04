package com.example.shosekikun.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("error")
class ErrorController {
    @GetMapping("/", "")
    fun error(model: Model): String {
        model.addAttribute("err", "err")
        return "error/error"
    }
}