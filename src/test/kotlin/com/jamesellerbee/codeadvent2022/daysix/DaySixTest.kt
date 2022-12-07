package com.jamesellerbee.codeadvent2022.daysix

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DaySixTest {
    @ParameterizedTest
    @CsvSource(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb, 7",
        "bvwbjplbgvbhsrlpgdmjqwftvncz, 5",
        "nppdvjthqldpwncqszvftbrmjlhg, 6",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg, 10",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw, 11",
    )
    fun testExampleInputPartOne(input: String, expected: Int) {
        // Given day six
        val daySix = DaySix { emptyList() }

        // When decoding the input
        val actual = daySix.decode(input)

        // Then it matches the expected
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb, 19",
        "bvwbjplbgvbhsrlpgdmjqwftvncz, 23",
        "nppdvjthqldpwncqszvftbrmjlhg, 23",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg, 29",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw, 26"
    )
    fun testExampleInputPartTwo(input: String, expected: Int) {
        // Given day six
        val daySix = DaySix { emptyList() }

        // When decoding the input
        val actual = daySix.decode(input, 14)

        // Then it matches the expected
        assertEquals(expected, actual)
    }
}