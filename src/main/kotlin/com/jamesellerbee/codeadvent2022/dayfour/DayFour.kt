package com.jamesellerbee.codeadvent2022.dayfour

import com.jamesellerbee.codeadvent2022.AdventDay

class DayFour(inputProvider: () -> List<String>) : AdventDay("Day Four", "Camp Cleanup", inputProvider) {
    override fun partOne() {
        /*
        Space needs to be cleared before the last supplies can be unloaded from the ships,
        and so several Elves have been assigned the job of cleaning up sections of the camp.
        Every section has a unique ID number, and each Elf is assigned a range of section IDs.

        However, as some Elves compare their section assignments with each other,
        they've noticed that many of the assignments overlap.
        To try to quickly find overlaps and reduce duplicated effort,
        the Elves pair up and make a big list of the section assignments for each pair (your puzzle input).

        In how many assignment pairs does one range fully contain the other?
         */
        printOutput(
            expectedAnswerProvider?.getExpectedAnswer("Day Four", "Part One") ?: "UNKNOWN",
            calculateNumCompletelyOverlappingPairs(inputProvider.invoke(), OverlapMode.SUB_SET).toString()
        )
    }

    override fun partTwo() {
        /*
        It seems like there is still quite a bit of duplicate work planned.
        Instead, the Elves would like to know the number of pairs that overlap at all.
         */
        printOutput(
            expectedAnswerProvider?.getExpectedAnswer("Day Four", "Part Two") ?: "UNKNOWN",
            calculateNumCompletelyOverlappingPairs(inputProvider.invoke(), OverlapMode.INTERSECTION).toString()
        )
    }

    fun calculateNumCompletelyOverlappingPairs(input: List<String>, overlapMode: OverlapMode): Int {
        var sum = 0
        input.forEach {
            val tokens = it.split(",")
            val rangeA = tokens[0]
            val rangeB = tokens[1]

            if (isRangeOverlapping(rangeA, rangeB, overlapMode)) {
                sum++
            }
        }

        return sum
    }

    private fun isRangeOverlapping(sectionRangeA: String, sectionRangeB: String, overlapMode: OverlapMode): Boolean {
        val tokensA = sectionRangeA.split("-")
        val tokensB = sectionRangeB.split("-")

        val rangeA = tokensA[0].toInt()..tokensA[1].toInt()
        val rangeB = tokensB[0].toInt()..tokensB[1].toInt()



        return when(overlapMode) {
            OverlapMode.SUB_SET -> isSubSet(rangeA, rangeB) || isSubSet(rangeB, rangeA)
            OverlapMode.INTERSECTION -> containsIntersection(rangeA, rangeB)
        }


    }

    private fun isSubSet(rangeA: ClosedRange<Int>, rangeB: ClosedRange<Int>): Boolean {
        var result = true

        // rangeB is considered a subset of rangeA if and only if rangeB contains every element in range A
        for (i in rangeB.start..rangeB.endInclusive) {
            result = result && rangeA.contains(i)
        }

        return result
    }

    private fun containsIntersection(rangeA: ClosedRange<Int>, rangeB: ClosedRange<Int>): Boolean {
        var result = false

        // rangeB is considered a subset of rangeA if and only if rangeB contains every element in range A
        for (i in rangeB.start..rangeB.endInclusive) {
            result = result || rangeA.contains(i)
        }

        return result
    }
}