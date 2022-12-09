package com.jamesellerbee.codeadvent2022.dayeight

import com.jamesellerbee.codeadvent2022.AdventDay

class DayEight(inputProvider: () -> List<String>) : AdventDay("Day Eight", "Treetop Tree House", inputProvider) {
    override fun partOne() {
        TODO("Not yet implemented")
    }

    override fun partTwo() {
        TODO("Not yet implemented")
    }

    fun findNumTreesVisible(input: List<String>): Int {
        val gridCells = buildGrid(input)

        var numTreesVisible = 0

        gridCells.forEach {
            if (it.up == null || it.down == null || it.left == null || it.right == null) {
                println(" edge ${it.tree?.height} (${it.row}, ${it.column})")
                numTreesVisible++
            } else if (compareToEdge(it, Direction.UP)
                && compareToEdge(it, Direction.DOWN)
                && compareToEdge(it, Direction.LEFT)
                && compareToEdge(it, Direction.RIGHT)
            ) {
                println("interior ${it.tree?.height} (${it.row}, ${it.column})")
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

        gridCells.forEach {
            println(it.toString())
        }

        return gridCells
    }

    private fun compareToEdge(cell: GridCell, direction: Direction): Boolean {
        val neighbor = when (direction) {
            Direction.UP -> cell.up
            Direction.DOWN -> cell.down
            Direction.LEFT -> cell.left
            Direction.RIGHT -> cell.right
        } ?: return true

        return cell.tree?.height!! < neighbor.tree?.height!! && compareToEdge(neighbor, direction)
    }
}