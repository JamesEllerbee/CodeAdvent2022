package com.jamesellerbee.codeadvent2022.utility

import java.io.File

class FileUtility {
    fun readLineDelimitedInput(filePath: String): List<String> {
        val lines = mutableListOf<String>()

        val file = File(filePath)

        file.forEachLine {
            lines.add(it)
        }

        return lines
    }
}