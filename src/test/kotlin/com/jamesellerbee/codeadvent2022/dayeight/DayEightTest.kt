package com.jamesellerbee.codeadvent2022.dayeight

import com.jamesellerbee.codeadvent2022.utility.FileUtility
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DayEightTest {
    private val fileUtility = FileUtility()
    private val exampleFilePath = "day8/Day8ExampleInput.txt"

    @Test
    fun testExampleInputPartOne() {
        // Given example input
        val exampleInput = fileUtility.readLineDelimitedInput(exampleFilePath)

        // Given day eight
        val dayEight = DayEight { listOf() }

        // When determining number of trees visible from outside the grid
        val actual = dayEight.findNumTreesVisible(exampleInput)

        // Then it matches the expected
        assertEquals(21, actual)
    }
}