package advent

import java.io.File
import java.net.URL



class Day02(private val input: URL) {

    enum class Action(val point: Int) {
        ROCK(1), PAPER(2), SCISSORS(3)
    }

    enum class Outcome(val point: Int) {
        LOST(0), DRAW(3), WIN(6)
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

        val outcome = when(round) {
            // lost
            Round(Action.ROCK, Action.SCISSORS) -> Outcome.LOST
            Round(Action.PAPER, Action.ROCK) -> Outcome.LOST
            Round(Action.SCISSORS, Action.PAPER) -> Outcome.LOST

            // draw
            Round(Action.ROCK, Action.ROCK) -> Outcome.DRAW
            Round(Action.PAPER, Action.PAPER) -> Outcome.DRAW
            Round(Action.SCISSORS, Action.SCISSORS) -> Outcome.DRAW

            // win
            Round(Action.ROCK, Action.PAPER) -> Outcome.WIN
            Round(Action.PAPER, Action.SCISSORS) -> Outcome.WIN
            Round(Action.SCISSORS, Action.ROCK) -> Outcome.WIN

            else -> throw IllegalArgumentException()
        }

        return outcome.point + round.you.point
    }

    fun solvePart1(): Int {
        return File(input.toURI()).readLines()
            .map { extract(it) }
            .sumOf { score(it) }
    }

    private fun extractPart2(line: String) : Round {
        val split = line.split(" ")

        val him = when(split[0]) {
            "A" -> Action.ROCK
            "B" -> Action.PAPER
            "C" -> Action.SCISSORS
            else -> throw IllegalArgumentException()
        }

        val outcome = when(split[1]) {
            "X" -> Outcome.LOST
            "Y" -> Outcome.DRAW
            "Z" -> Outcome.WIN
            else -> throw IllegalArgumentException()
        }

        return Round(him, predictYourPlay(him, outcome))
    }

    private fun predictYourPlay(him: Action, outcome: Outcome) : Action {
        data class PredictionInput(val him: Action, val outcome: Outcome)
        return when(PredictionInput(him, outcome)) {
            // lost
            PredictionInput(Action.ROCK, Outcome.LOST) -> Action.SCISSORS
            PredictionInput(Action.PAPER, Outcome.LOST) -> Action.ROCK
            PredictionInput(Action.SCISSORS, Outcome.LOST) -> Action.PAPER

            // draw
            PredictionInput(Action.ROCK, Outcome.DRAW) -> Action.ROCK
            PredictionInput(Action.PAPER, Outcome.DRAW) -> Action.PAPER
            PredictionInput(Action.SCISSORS, Outcome.DRAW) -> Action.SCISSORS

            // win
            PredictionInput(Action.ROCK, Outcome.WIN) -> Action.PAPER
            PredictionInput(Action.PAPER, Outcome.WIN) -> Action.SCISSORS
            PredictionInput(Action.SCISSORS, Outcome.WIN) -> Action.ROCK

            else -> throw IllegalArgumentException()
        }
    }

    fun solvePart2(): Int {
        return File(input.toURI()).readLines()
            .map { extractPart2(it) }.sumOf { score(it) }
    }
}