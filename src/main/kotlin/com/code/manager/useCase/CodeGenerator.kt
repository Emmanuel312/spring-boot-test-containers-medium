package com.code.manager.useCase

import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class CodeGenerator {

    fun generate(): String {
        val listOfNumbers = List(6) {
            Random.nextInt(9)
        }

        return listOfNumbers.joinToString("")
    }
}