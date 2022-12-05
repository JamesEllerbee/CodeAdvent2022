package com.jamesellerbee.codeadvent2022.utility

class ExpectedAnswerProvider {
    private val answerMap = mapOf(
        Pair(
            "Day One",
            mapOf(
                Pair("Part One", "70296"),
                Pair("Part Two", "205381")
            )
        ),

        Pair(
            "Day Two",
            mapOf(
                Pair("Part One", "8933"),
                Pair("Part Two", "11998")
            )
        ),

        Pair(
            "Day Three",
            mapOf(
                Pair("Part One", "7872"),
                Pair("Part Two", "2497")
            )
        )
    )

    fun getExpectedAnswer(day: String, part: String): String {
        return answerMap[day]?.get(part) ?: "UNKNOWN"
    }
}