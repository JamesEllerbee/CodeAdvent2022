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
        println("Not yet implemented")
    }

    fun findNumTreesVisible(input: List<String>): Int {
        val gridCells = buildGrid(input)

        var numTreesVisible = 0

        gridCells.forEach {
            if (it.up == null || it.down == null || it.left == null || it.right == null) {
                logger?.log(LoggingLevel.DEBUG, "edge ${it.tree?.height} (${it.row}, ${it.column})")
                numTreesVisible++
            } else if (compareTo(it.tree?.height!!, it, Direction.UP)
                || compareTo(it.tree?.height!!, it, Direction.DOWN)
                || compareTo(it.tree?.height!!, it, Direction.LEFT)
                || compareTo(it.tree?.height!!, it, Direction.RIGHT)
            ) {
                if (logger?.isLevelEnabled(LoggingLevel.DEBUG) == true) {
                    val directionVisible = StringBuilder()

                    if (compareTo(it.tree?.height!!, it, Direction.UP)) directionVisible.append(" top")
                    if (compareTo(it.tree?.height!!, it, Direction.DOWN)) directionVisible.append(" bottom")
                    if (compareTo(it.tree?.height!!, it, Direction.LEFT)) directionVisible.append(" left")
                    if (compareTo(it.tree?.height!!, it, Direction.RIGHT)) directionVisible.append(" right")

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

        if(logger?.isLevelEnabled(LoggingLevel.DEBUG) == true) {
            gridCells.forEach {
                logger?.log(LoggingLevel.DEBUG, it.toString())
            }
        }

        return gridCells
    }

    private fun compareTo(height: Int, cell: GridCell, direction: Direction): Boolean {
        val neighbor = when (direction) {
            Direction.UP -> cell.up
            Direction.DOWN -> cell.down
            Direction.LEFT -> cell.left
            Direction.RIGHT -> cell.right
        } ?: return true

        val taller = height > neighbor.tree?.height!!
        return taller && compareTo(height, neighbor, direction)
    }
}