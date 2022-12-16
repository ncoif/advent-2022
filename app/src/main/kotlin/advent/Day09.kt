package advent

import java.io.File
import java.net.URL
import kotlin.math.abs

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

    data class Position(val x: Int, val y: Int) {
        fun applyDirection(direction: Direction) : Position {
            return when(direction) {
                Direction.LEFT -> Position(x - 1, y)
                Direction.RIGHT -> Position(x + 1, y)
                Direction.UP -> Position(x, y + 1)
                Direction.DOWN -> Position(x, y - 1)
            }
        }

        fun isNeighbour(other: Position) : Boolean{
            return when(other) {
                Position(x, y) -> true // same
                Position(x + 1, y) -> true // right
                Position(x + 1, y + 1) -> true // top right
                Position(x, y + 1) -> true // top
                Position(x - 1, y + 1) -> true // top left
                Position(x - 1, y) -> true // left
                Position(x - 1, y - 1) -> true // bottom left
                Position(x, y - 1) -> true // bottom
                Position(x + 1, y - 1) -> true // bottom right
                else -> false
            }
        }

        fun allNeighbour(): List<Position> {
            return listOf(
                Position(x + 1, y), // right
                Position(x, y + 1), // top
                Position(x - 1, y), // left
                Position(x, y - 1), // bottom
                Position(x + 1, y + 1), // top right
                Position(x - 1, y + 1), // top left
                Position(x - 1, y - 1), // bottom left
                Position(x + 1, y - 1), // bottom right
            )
        }

        fun distance(other: Position) : Int {
            return (other.x - x) * (other.x - x) + (other.y - y) * (other.y - y)
        }
    }

    data class State(
        var headPosition : Position,
        var ropeLength: Int, var tailPosition: Array<Position>,
        val pastTailPositions : MutableSet<Position>) {

        fun playMove(move: Move) {
            //println(move)
            (0 until move.distance).forEach{ _ ->
                playDirection(move.direction)
                moveTail()
            }
        }

        private fun playDirection(direction: Direction) {
            val newHeadPosition = headPosition.applyDirection(direction)
            headPosition = newHeadPosition
        }

        private fun moveTail() {
            for (t in 0 until ropeLength) {
                val previousTailPosition = if (t == 0) { headPosition } else { tailPosition[t - 1] }

                if (!tailPosition[t].isNeighbour(previousTailPosition)) {
                    val newTailPosition = findNewTailPosition(t)
                    tailPosition[t] = newTailPosition
                }
            }
            pastTailPositions.add(tailPosition[ropeLength - 1])
        }

        private fun findNewTailPosition(tailIdx: Int) : Position {
            // try all neighbour position until one is valid
            val previousTailSegment = if (tailIdx == 0) { headPosition } else { tailPosition[tailIdx - 1] }

            var minDistance = 1000
            var closestPosition = tailPosition[tailIdx]
            for (neighbour in tailPosition[tailIdx].allNeighbour()) {
                val distanceToHead = neighbour.distance(previousTailSegment)
                if (distanceToHead < minDistance) {
                    minDistance = distanceToHead
                    closestPosition = neighbour
                }
            }
            return closestPosition
        }

        data class DisplayBoard(val width: Int, val height: Int) {

            private val board = mutableListOf<String>()
            private val boardW = 2 * width + 1
            private val boardH = 2 * height + 1
            init {
                (0 until boardW * boardH).forEach{ _ -> board.add(".") }
            }

            fun insertAt(w: Int, h: Int, value: String) {
                board[(w + width) + boardW * (h + height)] = value
            }

            override fun toString(): String {
                val sb = StringBuilder()
                for (h in (0 until boardH).reversed()){
                    for (w in 0 until boardW){
                        sb.append(board[w + boardW * h])
                    }
                    sb.append(System.lineSeparator())
                }
                sb.append(System.lineSeparator())
                return sb.toString()
            }
        }

        private fun printDebug() {
            val allPositions = mutableListOf(headPosition)
            tailPosition.forEach { allPositions.add(it) }
            pastTailPositions.forEach { allPositions.add(it) }

            val width = allPositions.maxOf { abs(it.x) } + 1
            val height = allPositions.maxOf { abs(it.y) } + 1

            val displayBoard = DisplayBoard(width, height)

            //pastTailPositions.forEach { board[it.x + width * it.y] = "#" }
            tailPosition.forEachIndexed{ idx, element -> displayBoard.insertAt(element.x, element.y, (idx + 1).toString()) }
            displayBoard.insertAt(headPosition.x, headPosition.y, "H")

            println(displayBoard)
        }
    }

    private fun initialState(ropeLength: Int) : State {
        val rope = Array(ropeLength) { _ -> Position(0, 0) }
        return State(Position(0, 0), ropeLength, rope, mutableSetOf(Position(0, 0)))
    }

    fun solvePart1(): Int {
        val input = File(input.toURI()).readLines()
        val moves = input.map { extractMove(it) }

        val state = initialState(1)

        moves.forEach { state.playMove(it) }

        return state.pastTailPositions.size
    }

    fun solvePart2(): Int {
        val input = File(input.toURI()).readLines()
        val moves = input.map { extractMove(it) }

        val state = initialState(9)

        moves.forEach { state.playMove(it) }

        return state.pastTailPositions.size
    }
}