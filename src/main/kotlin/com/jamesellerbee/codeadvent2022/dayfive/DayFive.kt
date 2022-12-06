package com.jamesellerbee.codeadvent2022.dayfive

import com.jamesellerbee.codeadvent2022.AdventDay
import com.jamesellerbee.codeadvent2022.utility.logging.LoggingLevel
import java.lang.StringBuilder
import java.util.Stack

class DayFive(inputProvider: () -> List<String>) : AdventDay("Day Five", "Supply Stacks", inputProvider) {
    override fun partOne() {
        printOutput(
            expectedAnswerProvider?.getExpectedAnswer("Day Fix", "Part One") ?: "UNKNOWN",
            getCratesAtTopOfEachStack(inputProvider.invoke())
        )
    }

    override fun partTwo() {
        TODO("Not yet implemented")
    }

    fun getCratesAtTopOfEachStack(input: List<String>): String {
        // initialize stacks
        val stacks = createStacks(determineNumStacks(input))
        input.filter { it.contains("[") }.forEach {
            logger?.log(LoggingLevel.DEBUG, it)
            fillStacksWithLine(stacks, it)
        }

        logger?.log(LoggingLevel.DEBUG, "Before flip")
        logStacksContents(stacks)

        // flip each stack
        flipStacks(stacks)

        logger?.log(LoggingLevel.DEBUG, "After flip")
        logStacksContents(stacks)

        val instructions = input.filter { it.contains("move") }

        instructions.forEach {
            val tokens = it.split(" ")
            val amountToMove = tokens[1].toInt()
            val source = tokens[3].toInt()
            val destination = tokens.last().toInt()

            logger?.log(
                LoggingLevel.DEBUG,
                "amountToMove = $amountToMove, source = $source, destination = $destination"
            )
            for (i in 0 until amountToMove) {
                stacks[destination - 1].push(stacks[source - 1].pop())
            }

            logStacksContents(stacks)
        }

        // build message for the crates on top of each stack
        val messageBuilder = StringBuilder()
        for (stack in stacks) {
            messageBuilder.append(stack.peek())
        }

        return messageBuilder.toString()
    }

    private fun flipStacks(stacks: MutableList<Stack<String>>) {
        stacks.forEachIndexed { index, stack ->
            val crates = Stack<String>()
            while (stack.isNotEmpty()) {
                crates.push(stack.pop())
            }
            stacks[index] = crates
        }
    }

    private fun logStacksContents(stacks: MutableList<Stack<String>>) {
        if (logger?.isLevelEnabled(LoggingLevel.DEBUG) == true) {
            // before moving instructions
            stacks.forEachIndexed { index, stack ->
                logger?.log(LoggingLevel.DEBUG, "Stack ${index + 1}: $stack")
            }
        }
    }

    private fun fillStacksWithLine(stacks: MutableList<Stack<String>>, line: String) {
        // traverse characters in line, if found '[', then note where in the line it was found to determine index of stack to add the crate to
        var numSpaces = 0
        for (i in line.indices) {
            if (line[i] == '[') {
                logger?.log(
                    LoggingLevel.DEBUG,
                    "number of spaces encounters = $numSpaces, goes in stack number ${(numSpaces / 4) + 1}"
                )
                stacks[numSpaces / 4].push(line[i + 1].toString())
            }

            numSpaces += 1
        }
    }

    private fun createStacks(numStacks: Int): MutableList<Stack<String>> {
        val stacks = mutableListOf<Stack<String>>()
        for (i in 1..numStacks) {
            stacks.add(Stack())
        }
        return stacks
    }

    private fun determineNumStacks(input: List<String>): Int {
        var numStacks = 0
        val linesWithNumbers = input.filter { it.isNotEmpty() && !it.contains(Regex("\\[|m")) }
        val lineWithNumbers = linesWithNumbers[0].split(Regex(" +"))
        lineWithNumbers.forEach {
            if (it.toIntOrNull() != null && it.toInt() > numStacks) {
                numStacks = it.toInt()
            }
        }
        return numStacks
    }
}