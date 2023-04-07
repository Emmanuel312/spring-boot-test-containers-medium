package com.code.manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CodeManagerApplication

fun main(args: Array<String>) {
	runApplication<CodeManagerApplication>(*args)
}
