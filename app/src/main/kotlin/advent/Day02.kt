package advent

import java.io.File
import java.net.URL



class Day02(private val input: URL) {

    enum class Action(val point: Int) {
        ROCK(1), PAPER(2), SCISSORS(3)
    }

    data class Round(val him: Action, val you: Action)

    private fun extract(line: String) : Round {
        val split = line.split(" ")

        val him = when(split[0]) {
            "A" -> Action.ROCK
            "B" -> Action.PAPER
            "C" -> Action.SCISSORS
            else -> throw IllegalArgumentException()
        }

        val you = when(split[1]) {
            "X" -> Action.ROCK
            "Y" -> Action.PAPER
            "Z" -> Action.SCISSORS
            else -> throw IllegalArgumentException()
        }

        return Round(him, you)
    }

    private fun score(round : Round) : Int {

        val winner = when(round) {
            // lost
            Round(Action.ROCK, Action.SCISSORS) -> 0
            Round(Action.PAPER, Action.ROCK) -> 0
            Round(Action.SCISSORS, Action.PAPER) -> 0

            // draw
            Round(Action.ROCK, Action.ROCK) -> 3
            Round(Action.PAPER, Action.PAPER) -> 3
            Round(Action.SCISSORS, Action.SCISSORS) -> 3

            // win
            Round(Action.ROCK, Action.PAPER) -> 6
            Round(Action.PAPER, Action.SCISSORS) -> 6
            Round(Action.SCISSORS, Action.ROCK) -> 6

            else -> throw IllegalArgumentException()
        }

        return winner + round.you.point
    }

    fun solvePart1(): Int {
        return File(input.toURI()).readLines()
            .map { extract(it) }.sumOf { score(it) }
    }
}