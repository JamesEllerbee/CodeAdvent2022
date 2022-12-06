package com.jamesellerbee.codeadvent2022.daytwo

import com.jamesellerbee.codeadvent2022.utility.DependencyInjector
import com.jamesellerbee.codeadvent2022.utility.FileUtility
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DayTwoTest {
    private val exampleInputFilePath = "day2/Day2ExampleInput.txt"
    private val fileUtility = FileUtility()
    private var dayTwo = DayTwo() { emptyList() }

    @Test
    fun testProvidedExample1() {
        val exampleInput = fileUtility.readLineDelimitedInput(exampleInputFilePath)

        assertEquals(15, dayTwo.determineScore(exampleInput) {
            return@determineScore dayTwo.pickPlay(it)
        })
    }

    @Test
    fun testProvidedExample2() {
        val exampleInput = fileUtility.readLineDelimitedInput(exampleInputFilePath)

        assertEquals(12, dayTwo.determineScore(exampleInput) {
            return@determineScore dayTwo.pickOutcome(it)
        })
    }

    @Test
    fun testAllDrawsWhenPickingPlay() {
        val testInput = listOf("A X", "B Y", "C Z")

        assertEquals(15, dayTwo.determineScore(testInput) {
            return@determineScore dayTwo.pickPlay(it)
        })
    }

    @Test
    fun testAllLosesWhenPickingPlay() {
        val testInput = listOf("B X", "C Y", "A Z")

        assertEquals(6, dayTwo.determineScore(testInput) {
            return@determineScore dayTwo.pickPlay(it)
        })
    }

    @Test
    fun testAllLosesWhenPickingOutcome() {
        val testInput = listOf("A X", "B X", "C X")

        assertEquals(6, dayTwo.determineScore(testInput) {
            return@determineScore dayTwo.pickOutcome(it)
        })

    }

    @Test
    fun testAllWinsWhenPickingOutcome() {
        val testInput = listOf("A Z", "B Z", "C Z")

        assertEquals(24, dayTwo.determineScore(testInput) {
            return@determineScore dayTwo.pickOutcome(it)
        })
    }
}