package com.jamesellerbee.codeadvent2022.daythree

import com.jamesellerbee.codeadvent2022.AdventDay
import com.jamesellerbee.codeadvent2022.utility.DependencyInjector
import com.jamesellerbee.codeadvent2022.utility.ExpectedAnswerProvider
import com.jamesellerbee.codeadvent2022.utility.logging.Logger
import com.jamesellerbee.codeadvent2022.utility.logging.LoggingLevel

class DayThree(inputProvider: () -> List<String>) :
    AdventDay("Day Three", inputProvider) {
    private val lowerCaseOffset = 96
    private val upperCaseOffset = 38

    private val logger by lazy {
        DependencyInjector.resolve<Logger>(Logger::class.java)
    }

    private val expectedAnswerProvider by lazy {
        DependencyInjector.resolve<ExpectedAnswerProvider>(ExpectedAnswerProvider::class.java)
    }

    fun findCommonCharacter(groups: List<String>): Char {
        var commonChar = Char(0)
        for(i in groups[0].indices) {
            val desiredItem = groups[0][i]

            var othersContainDesiredItem = true
            for(j in 1 until groups.size) {
                val items = groups[j].toCharArray().filter { it == desiredItem }
                othersContainDesiredItem = othersContainDesiredItem && items.isNotEmpty()
            }

            if(othersContainDesiredItem) {
                commonChar = desiredItem
            }
        }

        return commonChar

    }

    fun determinePriority(character: Char): Int {
        return if (character.code in 65..90) character.code - upperCaseOffset else if (character.code in 97..122) character.code - lowerCaseOffset else 0
    }


    fun findSumOfPriorities(input: List<String>, numLines: Int): Int {
        var sum = 0

        var lines = mutableListOf<String>()

        // for each "rucksack"
        input.forEach {

            lines.add(it)

            if(lines.size == numLines) {
                // find the character that each "grouping" has in common
                val commonItem = if(numLines == 1)
                    findCommonCharacter(listOf(it.substring(0, it.length / 2), it.substring(it.length / 2, it.length)))
                else
                    findCommonCharacter(lines)

                // find value of the priority of the common item
                val priority = determinePriority(commonItem)
                logger?.log(LoggingLevel.DEBUG, "Common item = $commonItem. Priority is $priority")
                sum += priority

                lines = mutableListOf()
            }
        }

        return sum
    }



    override fun partOne() {
        val expected = expectedAnswerProvider?.getExpectedAnswer("Day Three", "Part One") ?: "UNKNOWN"
        val actual = findSumOfPriorities(inputProvider.invoke(),1)

        printOutput(expected, actual.toString())
    }

    override fun partTwo() {
        val expected = expectedAnswerProvider?.getExpectedAnswer("Day Three", "Part Two") ?: "UNKNOWN"
        val actual = findSumOfPriorities(inputProvider.invoke(),3)

        printOutput(expected, actual.toString())
    }

}