package com.example.shosekikun

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//@SpringBootApplication(scanBasePackages = ["com.example.shosekikun.*"])
@SpringBootApplication(scanBasePackages= [
	"com.example.shosekikun.entity.*",
	"com.example.shosekikun.mapper.*",
	"com.example.shosekikun.repository.*",
	"com.example.shosekikun.repositoryImpl.*",
	"com.example.shosekikun.usecase.*",
])

class ShosekikunApplication

fun main(args: Array<String>) {
	runApplication<ShosekikunApplication>(*args)
}
