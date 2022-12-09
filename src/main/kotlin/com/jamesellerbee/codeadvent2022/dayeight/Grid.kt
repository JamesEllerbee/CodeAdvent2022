package com.jamesellerbee.codeadvent2022.dayeight

class GridCell(val row: Int, val column: Int) {
    var tree: Tree? = null
    var up: GridCell? = null
    var down: GridCell? = null
    var left: GridCell? = null
    var right: GridCell? = null

    override fun toString(): String {
        return "$tree ($row, $column), up = ${up?.tree ?: "null"}, down = ${down?.tree ?: "null"}, left = ${left?.tree ?: "null"}, right = ${right?.tree ?: "null"}"
    }
}