package com.jamesellerbee.codeadvent2022.dayfour

import com.jamesellerbee.codeadvent2022.utility.FileUtility
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DayFourTest {
    private val fileUtility = FileUtility()
    private val exampleInputFile = "day4/Day4ExampleInput.txt"

    @Test
    fun testExamplePartOne() {
        // Given example input
        val exampleInput = fileUtility.readLineDelimitedInput(exampleInputFile)

        // Given Advent Day four
        val dayFour = DayFour { emptyList() }

        // When calculating the number of overlapping pairs
        val actual = dayFour.calculateNumCompletelyOverlappingPairs(exampleInput, OverlapMode.SUB_SET)

        // Then the output matches the expected
        assertEquals(2, actual)
    }

    @Test
    fun testExamplePartTwo() {
        // Given example input
        val exampleInput = fileUtility.readLineDelimitedInput(exampleInputFile)

        // Given Advent Day four
        val dayFour = DayFour { emptyList() }

        // When calculating the number of overlapping pairs
        val actual = dayFour.calculateNumCompletelyOverlappingPairs(exampleInput, OverlapMode.INTERSECTION)

        // Then the output matches the expected
        assertEquals(4, actual)
    }
}