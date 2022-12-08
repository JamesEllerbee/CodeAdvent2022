package com.jamesellerbee.codeadvent2022.dayseven

import com.jamesellerbee.codeadvent2022.AdventDay
import com.jamesellerbee.codeadvent2022.utility.logging.LoggingLevel

class DaySeven(inputProvider: () -> List<String>) : AdventDay("Day Seven", "No Space Left On Device", inputProvider) {
    override fun partOne() {
        printOutput(
            expectedAnswerProvider?.getExpectedAnswer("Day Seven", "Part One") ?: "UNKNOWN",
            findSumOfSizesForDirectories(inputProvider.invoke(), 100000).toString()
        )
    }

    override fun partTwo() {
        printOutput(
            expectedAnswerProvider?.getExpectedAnswer("Day Seven", "Part Two") ?: "UNKNOWN",
            findSizeDirectoryOfAtleastSize(inputProvider.invoke(), 70000000, 30000000).toString()
        )
    }

    fun findSizeDirectoryOfAtleastSize(input: List<String>, maxSpace: Int, minimumSpace: Int): Int {
        val root = createFileSystem(input)
        val unusedSpace = maxSpace - root.getSize()
        val amountOfNeededSpace = minimumSpace - unusedSpace
        val matchingDirectories = mutableListOf<Directory>()
        findFloorHelper(root, matchingDirectories, amountOfNeededSpace)
        logger?.log(LoggingLevel.DEBUG, "Matching directories")
        matchingDirectories.forEach {
            logger?.log(LoggingLevel.DEBUG, "${it.name}, size = ${it.getSize()}")
        }
        return matchingDirectories.minOf { it.getSize() }
    }

    fun findSumOfSizesForDirectories(input: List<String>, minimumSpace: Int): Int {
        val root = createFileSystem(input)
        return findSumHelper(root, minimumSpace)
    }

    private fun findFloorHelper(pwd: Directory, matchingDirectoriesList: MutableList<Directory>, sizeFloor: Int) {
        logger?.log(LoggingLevel.DEBUG, "${pwd.name}, size = ${pwd.getSize()}")

        if (pwd.getSize() > sizeFloor) {
            matchingDirectoriesList.add(pwd)
        }

        pwd.children.filterIsInstance<Directory>().forEach {
            findFloorHelper(it, matchingDirectoriesList, sizeFloor)
        }
    }

    private fun findSumHelper(pwd: Directory, sizeCap: Int): Int {
        val dirSize = pwd.getSize()
        var size = if (dirSize > sizeCap) 0 else dirSize
        logger?.log(LoggingLevel.DEBUG, "size of ${pwd.name} = $dirSize")

        pwd.children.filterIsInstance<Directory>().forEach {
            size += findSumHelper(it, sizeCap)
        }

        return size
    }

    private fun createFileSystem(input: List<String>): Directory {
        val root = Directory("/")
        var presentWorkingDirectory = root
        input.forEachIndexed { index, line ->
            val tokens = line.split(" ")

            if ("$" in tokens) {
                logger?.log(LoggingLevel.DEBUG, "command is $line")
            }

            if ("ls" in tokens) {
                // create file system entities and add them to the present working directory
                // keep searching until another "$" is encountered
                var currentIndex = index + 1
                while (currentIndex < input.size && !input[currentIndex].contains("$")) {
                    addFileSystemEntity(input, currentIndex, presentWorkingDirectory)
                    currentIndex++
                }

                logger?.log(LoggingLevel.DEBUG, "Updated PWD with entities")
                logger?.log(LoggingLevel.DEBUG, presentWorkingDirectory.toString())

            } else if ("cd" in tokens) {
                // update present working directory
                if (tokens[2] == "..") {
                    val newPWD = presentWorkingDirectory.parent
                    newPWD?.let {
                        presentWorkingDirectory = it
                    }
                } else {
                    val fileSystemEntity = presentWorkingDirectory.getChild(tokens[2])
                    if (fileSystemEntity is Directory) {
                        presentWorkingDirectory = fileSystemEntity
                    }
                }

                logger?.log(LoggingLevel.DEBUG, "PWD is now ${presentWorkingDirectory.name}")
            }

        }

        logger?.log(LoggingLevel.DEBUG, "Final result:")
        logger?.log(LoggingLevel.DEBUG, root.toString())
        return root
    }

    private fun addFileSystemEntity(
        input: List<String>,
        currentIndex: Int,
        presentWorkingDirectory: Directory
    ) {
        val tokens = input[currentIndex].split(" ")
        if (input[currentIndex].contains("dir")) {
            presentWorkingDirectory.addChild(Directory(tokens[1]).also { it.parent = presentWorkingDirectory })
        } else {
            presentWorkingDirectory.addChild(File(tokens[1], tokens[0].toInt()))
        }
    }
}