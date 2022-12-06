package com.jamesellerbee.codeadvent2022

import com.jamesellerbee.codeadvent2022.constant.ConsoleColor
import com.jamesellerbee.codeadvent2022.utility.DependencyInjector
import com.jamesellerbee.codeadvent2022.utility.ExpectedAnswerProvider
import com.jamesellerbee.codeadvent2022.utility.logging.Logger

abstract class AdventDay(val dayNumber: String, val dayTitle: String, val inputProvider: () -> List<String>) {
    val logger by lazy {
        DependencyInjector.resolve<Logger>(Logger::class.java)
    }

    val expectedAnswerProvider by lazy {
        DependencyInjector.resolve<ExpectedAnswerProvider>(ExpectedAnswerProvider::class.java)
    }

    fun printOutput(expected: String, actual: String) {
        val color = when (expected) {
            actual -> ConsoleColor.GREEN
            "UNKNOWN" -> ""
            else -> ConsoleColor.RED
        }

        println("Expected = $expected, actual = $color$actual${if (color != "") ConsoleColor.CLEAR else ""}")
    }


    abstract fun partOne()

    abstract fun partTwo()
}