package com.jamesellerbee.codeadvent2022.utility.logging


abstract class Logger(val desiredLevel: LoggingLevel) {
    /**
     * Logs output.
     */
    abstract fun log(level: LoggingLevel, message: String);

    fun isLevelEnabled(level: LoggingLevel): Boolean {
        return level.ordinal <= desiredLevel.ordinal
    }
}