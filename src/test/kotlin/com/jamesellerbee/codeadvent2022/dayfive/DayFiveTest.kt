package com.jamesellerbee.codeadvent2022.dayfive

import com.jamesellerbee.codeadvent2022.utility.DependencyInjector
import com.jamesellerbee.codeadvent2022.utility.FileUtility
import com.jamesellerbee.codeadvent2022.utility.logging.ConsoleLogger
import com.jamesellerbee.codeadvent2022.utility.logging.Logger
import com.jamesellerbee.codeadvent2022.utility.logging.LoggingLevel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DayFiveTest {
    private val fileUtility = FileUtility()
    private val exampleInputFile = "day5/Day5ExampleInput.txt"

    @Test
    fun testExampleInputPartOne() {
        // Given example input
        val exampleInput = fileUtility.readLineDelimitedInput(exampleInputFile)

        // Given advent day five
        val dayFive = DayFive { emptyList() }

        // When calculating which crate will end up on the top of each stack
        val actual = dayFive.getCratesAtTopOfEachStack(exampleInput, CraneType.SINGLE)

        // Then the output matches the expected
        assertEquals("CMZ", actual)
    }

    @Test
    fun testExampleInputPartTwo() {
        // Given example input
        val exampleInput = fileUtility.readLineDelimitedInput(exampleInputFile)

        // Given advent day five
        val dayFive = DayFive { emptyList() }

        // When calculating which crate will end up on the top of each stack
        val actual = dayFive.getCratesAtTopOfEachStack(exampleInput, CraneType.MULTIPLE)

        // Then the output matches the expected
        assertEquals("MCD", actual)
    }

}