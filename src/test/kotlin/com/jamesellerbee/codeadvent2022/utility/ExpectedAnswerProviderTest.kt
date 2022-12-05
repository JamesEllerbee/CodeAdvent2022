package com.jamesellerbee.codeadvent2022.utility

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ExpectedAnswerProviderTest {
    @Test
    fun testReturnsUnknownWhenValueNotPresent() {
        // Given expected answer provider
        val provider = ExpectedAnswerProvider()

        // When querying for keys not present
        val actual = provider.getExpectedAnswer("DayZero", "Part Zero")

        // Then the value matches the expected
        assertEquals("UNKNOWN", actual)
    }

    @Test
    fun testReturnsExpectedWhenValuePresent() {
        // Given expected answer provider
        val provider = ExpectedAnswerProvider()

        // When querying for keys present
        val actual = provider.getExpectedAnswer("Day One", "Part One")

        // Then the value matches the expected
        assertEquals("70296", actual)
    }
}