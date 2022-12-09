package com.jamesellerbee.codeadvent2022.dayeight

import com.jamesellerbee.codeadvent2022.AdventDay
import com.jamesellerbee.codeadvent2022.utility.logging.LoggingLevel
import java.lang.StringBuilder

class DayEight(inputProvider: () -> List<String>) : AdventDay("Day Eight", "Treetop Tree House", inputProvider) {
    override fun partOne() {
        printOutput(
            expectedAnswerProvider?.getExpectedAnswer("Day Eight", "Part One") ?: "UKNOWN",
            findNumTreesVisible(inputProvider.invoke()).toString()
        )
    }

    override fun partTwo() {
        printOutput(
            expectedAnswerProvider?.getExpectedAnswer("Day Eight", "Part Two") ?: "UKNOWN",
            findScenicScore(inputProvider.invoke()).toString()
        )
    }

    fun findScenicScore(input: List<String>): Int {
        val gridCells = buildGrid(input)

        var max = 0
        var current = 0
        gridCells.forEach {
            val up = findNumTreesVisibleInDirection(it.tree?.height!!, it, Direction.UP)
            val down = findNumTreesVisibleInDirection(it.tree?.height!!, it, Direction.DOWN)
            val left = findNumTreesVisibleInDirection(it.tree?.height!!, it, Direction.LEFT)
            val right = findNumTreesVisibleInDirection(it.tree?.height!!, it, Direction.RIGHT)

            logger?.log(LoggingLevel.DEBUG, "scenic score for ${it.tree?.height} (${it.row}, ${it.column}) = $up * $left * $right * $down")

            current = up *
                    down *
                    left *
                    right

            if (current > max) {
                max = current
            }

            current = 0
        }

        return max
    }

    fun findNumTreesVisible(input: List<String>): Int {
        val gridCells = buildGrid(input)

        var numTreesVisible = 0

        gridCells.forEach {
            if (it.up == null || it.down == null || it.left == null || it.right == null) {
                logger?.log(LoggingLevel.DEBUG, "edge ${it.tree?.height} (${it.row}, ${it.column})")
                numTreesVisible++
            } else if (isTaller(it.tree?.height!!, it, Direction.UP)
                || isTaller(it.tree?.height!!, it, Direction.DOWN)
                || isTaller(it.tree?.height!!, it, Direction.LEFT)
                || isTaller(it.tree?.height!!, it, Direction.RIGHT)
            ) {
                if (logger?.isLevelEnabled(LoggingLevel.DEBUG) == true) {
                    val directionVisible = StringBuilder()

                    if (isTaller(it.tree?.height!!, it, Direction.UP)) directionVisible.append(" top")
                    if (isTaller(it.tree?.height!!, it, Direction.DOWN)) directionVisible.append(" bottom")
                    if (isTaller(it.tree?.height!!, it, Direction.LEFT)) directionVisible.append(" left")
                    if (isTaller(it.tree?.height!!, it, Direction.RIGHT)) directionVisible.append(" right")

                    logger?.log(
                        LoggingLevel.DEBUG,
                        "interior ${it.tree?.height} (${it.row}, ${it.column}) visible from$directionVisible"
                    )
                }

                numTreesVisible++
            }
        }

        return numTreesVisible
    }

    private fun buildGrid(input: List<String>): List<GridCell> {
        val gridCells = mutableListOf<GridCell>()
        var rowLength: Int? = null

        var row = 0
        var column = 0
        input.forEach { line ->
            rowLength = rowLength ?: line.length
            column = 0
            line.forEach { char ->
                val tree = Tree(char.toString().toInt())
                gridCells.add(GridCell(row, column).also { it.tree = tree })
                column++
            }

            row++
        }

        gridCells.forEachIndexed { index, cell ->
            if (index - 1 >= 0 && gridCells[index].row == gridCells[index - 1].row) {
                cell.left = gridCells[index - 1]
            }

            if (index + 1 < gridCells.size && gridCells[index].row == gridCells[index + 1].row) {
                cell.right = gridCells[index + 1]
            }

            if (index - rowLength!! >= 0
                && gridCells[index].row - 1 == gridCells[index - rowLength!!].row
            ) {
                cell.up = gridCells[index - rowLength!!]
            }

            if (index + rowLength!! < gridCells.size
                && gridCells[index].row + 1 == gridCells[index + rowLength!!].row
            ) {
                cell.down = gridCells[index + rowLength!!]
            }
        }

        if (logger?.isLevelEnabled(LoggingLevel.DEBUG) == true) {
            gridCells.forEach {
                logger?.log(LoggingLevel.DEBUG, it.toString())
            }
        }

        return gridCells
    }

    private fun isTaller(height: Int, cell: GridCell, direction: Direction): Boolean {
        val neighbor = when (direction) {
            Direction.UP -> cell.up
            Direction.DOWN -> cell.down
            Direction.LEFT -> cell.left
            Direction.RIGHT -> cell.right
        } ?: return true

        val taller = height > neighbor.tree?.height!!
        return taller && isTaller(height, neighbor, direction)
    }

    private fun findNumTreesVisibleInDirection(height: Int, cell: GridCell, direction: Direction): Int {
        val neighbor = when (direction) {
            Direction.UP -> cell.up
            Direction.DOWN -> cell.down
            Direction.LEFT -> cell.left
            Direction.RIGHT -> cell.right
        } ?: return 0

        val taller = height > neighbor.tree?.height!!
        return if (taller) 1 + findNumTreesVisibleInDirection(height, neighbor, direction) else 1
    }
}