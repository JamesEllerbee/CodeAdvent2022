package com.jamesellerbee.codeadvent2022.utility.logging

/**
 * A simple logger for printing messages to the console.
 * @property level The desired level. Anything logged at a level lower than this level will be filtered out.
 */
class ConsoleLogger(private val level: LoggingLevel = LoggingLevel.INFO) : Logger(level) {
    override fun log(level: LoggingLevel, message: String) {
        if (level.ordinal >= desiredLevel.ordinal) {
            println("[$level] $message")
        }
    }
}