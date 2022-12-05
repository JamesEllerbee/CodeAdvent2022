package com.jamesellerbee.codeadvent2022.dayone

import com.jamesellerbee.codeadvent2022.AdventDay
import com.jamesellerbee.codeadvent2022.utility.DependencyInjector
import com.jamesellerbee.codeadvent2022.utility.ExpectedAnswerProvider

class DayOne(inputProvider: () -> List<String>) :
    AdventDay("Day One", inputProvider) {
    private val expectedAnswerProvider by lazy {
        DependencyInjector.resolve<ExpectedAnswerProvider>(ExpectedAnswerProvider::class.java)
    }

    private fun findNMax(n: Int, input: List<String>): Int {
        if (input.isEmpty()) {
            return 0
        }

        val maxList = mutableListOf<Int>()

        while (maxList.size < n) {
            var cur = 0
            var max = 0

            input.forEach() {
                if (it.toIntOrNull() != null) {
                    cur += it.toInt()
                } else if (it == "") {
                    if (cur > max && (maxList.isEmpty() || cur < maxList.last())) {
                        max = cur
                    }
                    cur = 0
                }
            }

            maxList.add(max)
        }

        var sum = 0

        maxList.forEach {
            sum += it
        }

        return sum
    }

    override fun partOne() {
        /*
        The jungle must be too overgrown and difficult to navigate in vehicles or access from the air;
        the Elves' expedition traditionally goes on foot.
        As your boats approach land, the Elves begin taking inventory of their supplies.
        One important consideration is food - in particular, the number of Calories each Elf is carrying (your puzzle input).

        The Elves take turns writing down the number of Calories contained by the various meals, snacks, rations, etc.
        that they've brought with them, one item per line.
        Each Elf separates their own inventory from the previous Elf's inventory (if any) by a blank line.
         */
        val expected = expectedAnswerProvider?.getExpectedAnswer(dayNumber, "Part One") ?: "UNKNOWN"
        val actual = findNMax(1, inputProvider.invoke())

        printOutput(expected, actual.toString())
    }

    override fun partTwo() {
        /*
        By the time you calculate the answer to the Elves' question,
        they've already realized that the Elf carrying the most Calories of food might eventually run out of snacks.

        To avoid this unacceptable situation,
        the Elves would instead like to know the total Calories carried by the top three Elves carrying the most Calories.
        That way, even if one of those Elves runs out of snacks, they still have two backups.
         */

        val expected = expectedAnswerProvider?.getExpectedAnswer(dayNumber, "Part Two") ?: "UNKNOWN"
        val actual = findNMax(3, inputProvider.invoke())

        printOutput(expected, actual.toString())
    }
}