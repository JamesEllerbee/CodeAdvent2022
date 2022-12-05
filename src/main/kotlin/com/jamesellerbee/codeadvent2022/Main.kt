package com.jamesellerbee.codeadvent2022

import com.jamesellerbee.codeadvent2022.dayone.DayOne
import com.jamesellerbee.codeadvent2022.daythree.DayThree
import com.jamesellerbee.codeadvent2022.daytwo.DayTwo
import com.jamesellerbee.codeadvent2022.utility.DependencyInjector
import com.jamesellerbee.codeadvent2022.utility.ExpectedAnswerProvider
import com.jamesellerbee.codeadvent2022.utility.FileUtility
import com.jamesellerbee.codeadvent2022.utility.logging.ConsoleLogger
import com.jamesellerbee.codeadvent2022.utility.logging.Logger
import com.jamesellerbee.codeadvent2022.utility.logging.LoggingLevel

fun main(args: Array<String>) {
    loadDependencies()

    val adventDays = mapOf(
        Pair("1",
            DayOne {
                DependencyInjector
                    .resolve<FileUtility>(FileUtility::class.java)
                    ?.readLineDelimitedInput("day1/Day1Input.txt")
                    ?: listOf()
            }
        ),

        Pair(
            "2",
            DayTwo {
                DependencyInjector
                    .resolve<FileUtility>(FileUtility::class.java)
                    ?.readLineDelimitedInput("day2/Day2Input.txt")
                    ?: listOf()
            }

        ),

        Pair(
            "3",
            DayThree {
                DependencyInjector
                    .resolve<FileUtility>(FileUtility::class.java)
                    ?.readLineDelimitedInput("day3/Day3Input.txt")
                    ?: listOf()
            }
        )
    )

    adventDays.forEach {
        if(it.key in args || args.isEmpty()) {
            println(it.value.dayNumber)
            it.value.partOne()
            it.value.partTwo()
        }
    }
}

fun loadDependencies() {
    DependencyInjector.registerDependency(Logger::class.java, ConsoleLogger())
    DependencyInjector.registerDependency(FileUtility::class.java, FileUtility())
    DependencyInjector.registerDependency(ExpectedAnswerProvider::class.java, ExpectedAnswerProvider())
}