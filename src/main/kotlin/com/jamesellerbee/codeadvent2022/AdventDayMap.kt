package com.jamesellerbee.codeadvent2022

import com.jamesellerbee.codeadvent2022.dayfive.DayFive
import com.jamesellerbee.codeadvent2022.dayfour.DayFour
import com.jamesellerbee.codeadvent2022.dayone.DayOne
import com.jamesellerbee.codeadvent2022.daysix.DaySix
import com.jamesellerbee.codeadvent2022.daythree.DayThree
import com.jamesellerbee.codeadvent2022.daytwo.DayTwo
import com.jamesellerbee.codeadvent2022.utility.DependencyInjector
import com.jamesellerbee.codeadvent2022.utility.FileUtility

class AdventDayMap {
    val adventDays: Map<String, AdventDay>

    init {
        adventDays = mapOf(
            Pair("1",
                DayOne {
                    DependencyInjector
                        .resolve<FileUtility>(FileUtility::class.java)
                        ?.readLineDelimitedInput("day1/Day1Input.txt")
                        ?: emptyList()
                }
            ),

            Pair(
                "2",
                DayTwo {
                    DependencyInjector
                        .resolve<FileUtility>(FileUtility::class.java)
                        ?.readLineDelimitedInput("day2/Day2Input.txt")
                        ?: emptyList()
                }

            ),

            Pair(
                "3",
                DayThree {
                    DependencyInjector
                        .resolve<FileUtility>(FileUtility::class.java)
                        ?.readLineDelimitedInput("day3/Day3Input.txt")
                        ?: emptyList()
                }
            ),

            Pair(
                "4",
                DayFour {
                    DependencyInjector
                        .resolve<FileUtility>(FileUtility::class.java)
                        ?.readLineDelimitedInput("day4/Day4Input.txt")
                        ?: emptyList()
                }
            ),

            Pair(
                "5",
                DayFive {
                    DependencyInjector
                        .resolve<FileUtility>(FileUtility::class.java)
                        ?.readLineDelimitedInput("day5/Day5Input.txt")
                        ?: emptyList()
                }
            ),

            Pair(
                "6",
                DaySix {
                    DependencyInjector
                        .resolve<FileUtility>(FileUtility::class.java)
                        ?.readLineDelimitedInput("day6/Day6Input.txt")
                        ?: emptyList()
                }
            )

        )
    }
}