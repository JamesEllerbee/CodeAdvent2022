package com.jamesellerbee.codeadvent2022.dayseven

import com.jamesellerbee.codeadvent2022.utility.FileUtility
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DaySevenTest {
    private val fileUtility = FileUtility()
    private val exampleFilePath = "day7/Day7ExampleInput.txt"

    @Test
    fun testExampleInputPartOne() {
        // Given example input
        val exampleInput = fileUtility.readLineDelimitedInput(exampleFilePath)

        // Given day seven
        val daySeven = DaySeven { emptyList() }

        // When determining sum of sizes of directories
        val actual = daySeven.findSumOfSizesForDirectories(exampleInput, 100000)

        // Then the output matches the expected
        assertEquals(95437, actual)
    }

    @Test
    fun testExampleInputPartTwo() {
        // Given example input
        val exampleInput = fileUtility.readLineDelimitedInput(exampleFilePath)

        // Given day seven
        val daySeven = DaySeven { emptyList() }

        // When determining sum of sizes of directories
        val actual = daySeven.findSizeDirectoryOfAtleastSize(exampleInput, 70000000, 30000000)

        // Then the output matches the expected
        assertEquals(24933642, actual)
    }
}