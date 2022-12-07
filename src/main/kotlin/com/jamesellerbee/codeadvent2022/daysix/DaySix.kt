package com.jamesellerbee.codeadvent2022.daysix

import com.jamesellerbee.codeadvent2022.AdventDay

class DaySix(inputProvider: () -> List<String>) : AdventDay("Day Six", "Tuning Trouble", inputProvider) {
    override fun partOne() {
        printOutput(
            expectedAnswerProvider?.getExpectedAnswer("Day Six", "Part One") ?: "UNKNOWN",
            decode(inputProvider.invoke()[0]).toString()
        )
    }

    override fun partTwo() {
        val messageLength = 14
        printOutput(
            expectedAnswerProvider?.getExpectedAnswer("Day Six", "Part Two") ?: "UNKNOWN",
            decode(inputProvider.invoke()[0], messageLength).toString()
        )
    }

    fun decode(input: String, markerLength: Int = 4): Int {
        // the output is how many character have been "processed" before the marker is found
        // the marker is four non-repeating characters

        // for each character, look ahead the length of the mark and see if each is unique
        var marker = StringBuilder()
        var currentIndex = 0
        var endOfMarker = -1

        while (currentIndex < input.length && marker.length < markerLength) {
            var subIndex = currentIndex
            while (!marker.contains(input[subIndex]) && marker.length < markerLength && subIndex < input.length) {
                marker.append(input[subIndex])
                subIndex++
                endOfMarker = subIndex
            }

            if (marker.length != markerLength) {
                marker = StringBuilder()
            }
            currentIndex++
        }

        return endOfMarker
    }
}