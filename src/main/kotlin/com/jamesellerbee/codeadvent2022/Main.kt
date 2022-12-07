package com.jamesellerbee.codeadvent2022

import com.jamesellerbee.codeadvent2022.utility.DependencyInjector
import com.jamesellerbee.codeadvent2022.utility.ExpectedAnswerProvider
import com.jamesellerbee.codeadvent2022.utility.FileUtility
import com.jamesellerbee.codeadvent2022.utility.logging.ConsoleLogger
import com.jamesellerbee.codeadvent2022.utility.logging.Logger

fun main(args: Array<String>) {
    loadDependencies()

    AdventDayMap().adventDays.forEach {
        if (it.key in args || args.isEmpty()) {
            println("--- ${it.value.dayNumber}: ${it.value.dayTitle} ---")
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