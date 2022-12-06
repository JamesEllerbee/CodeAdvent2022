package com.jamesellerbee.codeadvent2022.daytwo

import com.jamesellerbee.codeadvent2022.AdventDay
import com.jamesellerbee.codeadvent2022.utility.DependencyInjector
import com.jamesellerbee.codeadvent2022.utility.ExpectedAnswerProvider
import com.jamesellerbee.codeadvent2022.utility.logging.Logger
import com.jamesellerbee.codeadvent2022.utility.logging.LoggingLevel

class DayTwo(inputProvider: () -> List<String>) :
    AdventDay("Day Two", "Rock Paper Scissors", inputProvider) {
    private val charOffSet = 23
    private val selectionFriendlyNameMap = mutableMapOf<String, String>()
    private val selectionScoreMap = mutableMapOf<String, Int>()

    init {
        // A, X = Rock
        selectionFriendlyNameMap["A"] = "Rock"
        selectionFriendlyNameMap["X"] = "Rock"
        selectionScoreMap["X"] = 1

        // B, Y = Paper
        selectionFriendlyNameMap["B"] = "Paper"
        selectionFriendlyNameMap["Y"] = "Paper"
        selectionScoreMap["Y"] = 2

        // C, Z = Scissors
        selectionFriendlyNameMap["C"] = "Scissors"
        selectionFriendlyNameMap["Z"] = "Scissors"
        selectionScoreMap["Z"] = 3

    }

    override fun partOne() {
        /*
The Elves begin to set up camp on the beach.
To decide whose tent gets to be closest to the snack storage, a giant Rock Paper Scissors tournament is already in progress.

Rock Paper Scissors is a game between two players.
Each game contains many rounds;
in each round, the players each simultaneously choose one of Rock, Paper, or Scissors using a hand shape.
Then, a winner for that round is selected: Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock. If both players choose the same shape, the round instead ends in a draw.

Appreciative of your help yesterday,
one Elf gives you an encrypted strategy guide (your puzzle input) that they say will be sure to help you win.
"The first column is what your opponent is going to play: A for Rock, B for Paper, and C for Scissors. The second column--" Suddenly,
the Elf is called away to help with someone's tent.

The second column, you reason, must be what you should play in response: X for Rock, Y for Paper, and Z for Scissors.
Winning every time would be suspicious, so the responses must have been carefully chosen.

The winner of the whole tournament is the player with the highest score.
Your total score is the sum of your scores for each round.
The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).

Since you can't be sure if the Elf is trying to help you or trick you,
you should calculate the score you would get if you were to follow the strategy guide.
         */

        val expected = expectedAnswerProvider?.getExpectedAnswer(dayNumber, "Part One") ?: "UNKNOWN"
        val actual = determineScore(inputProvider.invoke()) {
            pickPlay(it)
        }

        printOutput(expected, actual.toString())
    }

    override fun partTwo() {
        /*
"Anyway, the second column says how the round needs to end:
X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win.
Good luck!"
         */

        val expected = expectedAnswerProvider?.getExpectedAnswer(dayNumber, "Part Two") ?: "UNKNOWN"
        val actual = determineScore(inputProvider.invoke()) {
            pickOutcome(it)
        }

        printOutput(expected, actual.toString())
    }

    fun pickPlay(input: String): Int {
        val tokens = input.split(" ")
        val opponentInput = tokens[0]
        val yourInput = tokens[1]

        var outCome = OutCome.WIN
        if (yourInput[0].code + 1 == opponentInput[0].code + charOffSet
            || yourInput[0].code - 2 == opponentInput[0].code + charOffSet
        ) {
            outCome = OutCome.LOSE
        } else if (yourInput[0].code == opponentInput[0].code + charOffSet) {
            outCome = OutCome.DRAW
        }

        return outCome.worth + (selectionScoreMap[tokens[1]] ?: 0)
    }

    fun pickOutcome(input: String): Int {
        val tokens = input.split(" ")

        val opponentInput = tokens[0]

        val desiredOutcome = OutCome.fromSelection(tokens[1])

        // calculate what you need to play
        val selection = when (desiredOutcome) {
            OutCome.WIN ->
                if (opponentInput[0].code + charOffSet == 90) Char(opponentInput[0].code + charOffSet - 2).toString()
                else Char(opponentInput[0].code + charOffSet + 1).toString()

            OutCome.LOSE ->
                if (opponentInput[0].code + charOffSet == 88) Char(opponentInput[0].code + charOffSet + 2).toString()
                else Char(opponentInput[0].code + charOffSet - 1).toString()

            OutCome.DRAW -> Char(opponentInput[0].code + charOffSet).toString()
        }

        logger?.log(
            LoggingLevel.DEBUG,
            "input is ${selectionFriendlyNameMap[tokens[0]]},\ndesired outcome is $desiredOutcome,\nelection is ${selectionFriendlyNameMap[selection]} ($selection)"
        )

        return desiredOutcome.worth + (selectionScoreMap[selection] ?: 0)
    }

    fun determineScore(input: List<String>, outComeStrategy: (input: String) -> Int): Int {
        var totalScore = 0

        input.forEach {
            totalScore += outComeStrategy.invoke(it)
        }

        return totalScore
    }
}