package com.jamesellerbee.codeadvent2022

import com.jamesellerbee.codeadvent2022.constant.ConsoleColor

abstract class AdventDay(val dayNumber: String, val inputProvider: () -> List<String>) {
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