package advent

import java.io.File
import java.net.URL

class Day09(private val input: URL) {

    enum class Direction {
        LEFT, RIGHT, UP, DOWN
    }
    data class Move(val direction: Direction, val distance: Int)

    private fun extractMove(line: String): Move {
        val split = line.split(" ")

        val direction = when(split[0]) {
            "L" -> Direction.LEFT
            "R" -> Direction.RIGHT
            "U" -> Direction.UP
            "D" -> Direction.DOWN
            else -> throw IllegalArgumentException()
        }
        return Move(direction, split[1].toInt())
    }

    fun parseMoves(lines: List<String>) : List<Move> {
        return lines.map { extractMove(it) }
    }

    fun solvePart1(): Int {
        val input = File(input.toURI()).readLines()
        println(parseMoves(input))

        return 0
    }

}