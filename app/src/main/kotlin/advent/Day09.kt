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

    data class State(var headPosition : Position, var tailPosition: Position, val pastTailPositions : MutableSet<Position>) {
        fun playMove(move: Move) {
            //println(move)
            (0 until move.distance).forEach{ _ ->
                playDirection(move.direction)
                moveTail()
                //printDebug()
            }
        }

        private fun playDirection(direction: Direction) {
            val newHeadPosition = headPosition.applyDirection(direction)
            headPosition = newHeadPosition
        }

        private fun moveTail() {
            if (!tailPosition.isNeighbour(headPosition)) {
                val newTailPosition = findNewTailPosition()
                pastTailPositions.add(newTailPosition)
                tailPosition = newTailPosition
            }
        }

        private fun findNewTailPosition() : Position {
            // try all neighbour position until one is valid
            var minDistance = 1000
            var closestPosition = tailPosition
            for (neighbour in tailPosition.allNeighbour()) {
                val distanceToHead = neighbour.distance(headPosition)
                if (distanceToHead < minDistance) {
                    minDistance = distanceToHead
                    closestPosition = neighbour
                }
            }
            return closestPosition
        }

        fun printDebug() {
            val allPositions = mutableListOf(headPosition, tailPosition)
            pastTailPositions.forEach { allPositions.add(it) }

            val width = allPositions.maxOf { it.x } + 1
            val height = allPositions.maxOf { it.y } + 1

            val board = mutableListOf<String>()
            (0 until (width) * (height)).forEach{ _ -> board.add(".") }
            pastTailPositions.forEach { board[it.x + width * it.y] = "#" }
            board[tailPosition.x + width * tailPosition.y] = "T"
            board[headPosition.x + width * headPosition.y] = "H"

            for (h in height - 1 downTo 0){
                for (w in 0 until width){
                    print(board[w + width * h])
                }
                println()
            }
            println("====================")
        }
    }

    private fun initialState() : State {
        return State(Position(0, 0), Position(0, 0), mutableSetOf(Position(0, 0)))
    }

    fun solvePart1(): Int {
        val input = File(input.toURI()).readLines()
        val moves = input.map { extractMove(it) }

        val state = initialState()

        moves.forEach { state.playMove(it) }

        return state.pastTailPositions.size
    }

}