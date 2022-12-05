package com.jamesellerbee.codeadvent2022.daythree

import com.jamesellerbee.codeadvent2022.utility.DependencyInjector
import com.jamesellerbee.codeadvent2022.utility.FileUtility
import com.jamesellerbee.codeadvent2022.utility.logging.ConsoleLogger
import com.jamesellerbee.codeadvent2022.utility.logging.Logger
import com.jamesellerbee.codeadvent2022.utility.logging.LoggingLevel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DayThreeTest {
    private val fileUtility = FileUtility()
    private val dayThree = DayThree {
        listOf()
    }

    @Test
    fun testExamplePartOne() {
        DependencyInjector.registerDependency(Logger::class.java, ConsoleLogger(LoggingLevel.DEBUG))

        // Given the inputs from the example
        val exampleInput = fileUtility.readLineDelimitedInput("day3/Day3ExampleInput.txt")

        // When calculating the sum of the priorities
        val actual = dayThree.findSumOfPriorities(exampleInput, 1)

        // Then the sum matches the expected
        assertEquals(157, actual)
    }

    @Test
    fun testExamplePartTwo() {
        // Given the inputs from the example
        val exampleInput = fileUtility.readLineDelimitedInput("day3/Day3ExampleInput.txt")

        // When calculating the sum of the priorities
        val actual = dayThree.findSumOfPriorities(exampleInput, 3)

        // Then the sum matches the expected

    }

    @Test
    fun testCanDetermineCorrectPriority() {
        for (i in 65..90) {
            val commonChar = dayThree.findCommonCharacter(listOf(Char(i).toString(), Char(i).toString()))
            val priority = dayThree.determinePriority(commonChar)
            println("Common char is $commonChar, priority is $priority")
        }

        for(i in 97..122) {
            val commonChar = dayThree.findCommonCharacter(listOf(Char(i).toString(), Char(i).toString()))
            val priority = dayThree.determinePriority(commonChar)
            println("Common char is $commonChar, priority is $priority")
        }
    }
}