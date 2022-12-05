package com.jamesellerbee.codeadvent2022.daytwo

enum class OutCome(val worth: Int) {
    WIN(6),
    LOSE(0),
    DRAW(3);

    companion object {
        /**
         * Converts a selection; rock, paper, scissors, to an [OutCome].
         */
        fun fromSelection(selection: String): OutCome {
            return when(selection) {
                "X" -> LOSE
                "Y" -> DRAW
                "Z" -> WIN
                else -> LOSE
            }
        }
    }
}