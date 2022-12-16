package advent

import java.io.File
import java.net.URL

class Day14(private val input: URL) {

    enum class Type(val printChar: Char) {
        ROCK('#'),
        AIR('.'),
        SAND('o'),
        START('+')
    }
    data class Point(val x: Int, val y: Int)
    data class RockLine(val points: List<Point>)

    data class Grid(
        private val width: Int,
        private val height: Int,
        private val offsetX: Int,
        private val offsetY: Int = 0) {

        private val cells = mutableListOf<Type>()

        init {
            (0 until width * height).forEach{ _ -> cells.add(Type.AIR)}
            position(500, 0, Type.START)
        }

        private fun x(x: Int) = x - offsetX
        private fun y(y: Int) = y - offsetY

        private fun position(x: Int, y: Int, type: Type) {
            require(0 <= x(x) && x(x) < width ) { "out of bound" }
            cells[x(x) + y(y) * width] = type
        }

        private fun position(x: Int, y: Int) = cells[x(x) + y(y) * width]

        fun isOutsideGrid(x: Int, y: Int) : Boolean {
            return (x(x) < 0 || x(x) >= width || y(y) < 0 || y(y) >= height)
        }

        fun isBottom(y: Int) : Boolean {
            return y(y) >= height
        }

        fun print() {
            for (y in 0 until height) {
                for (x in 0 until width) {
                    print(cells[x + y * width].printChar)
                }
                println()
            }
            println()
        }

        fun fillRockLine(rockLine: RockLine) {
            require(rockLine.points.size >= 2) { "Rock line doesn't have enough points" }
            val iterator = rockLine.points.iterator()
            var previous = iterator.next()
            while (iterator.hasNext()) {
                val current = iterator.next()
                // trace line from previous to current
                fillRockLine(previous, current)

                // prepare next iteration
                previous = current
            }
        }

        private fun fillRockLine(start: Point, end: Point) {
            if (start.x == end.x) {
                //println("trace horizontal line from $start to $end")
                (minOf(start.y, end.y) .. maxOf(start.y, end.y)).forEach { position(start.x, it, Type.ROCK)}
            } else if(start.y == end.y) {
                //println("trace vertical line from $start to $end")
                (minOf(start.x, end.x) .. maxOf(start.x, end.x)).forEach { position(it, start.y, Type.ROCK)}
            } else {
                throw IllegalArgumentException("diagonal lines not supported")
            }
        }

        // return true if the sand was placed, false if the sand fell outside the board
        fun simulateOneSand() : Boolean {
            var current = Point(500, 0)
            while (true) {
                if (isOutsideGrid(current.x, current.y + 1)) {
                    return false
                } else if (isOutsideGrid(current.x - 1, current.y + 1)) {
                    return false
                } else if (isOutsideGrid(current.x + 1, current.y + 1)) {
                    return false
                } else if (position(current.x, current.y + 1) == Type.AIR) {
                    // can move under
                    current = Point(current.x, current.y + 1)
                } else if (position(current.x - 1, current.y + 1) == Type.AIR) {
                    // can move diagonal left
                    current = Point(current.x - 1, current.y + 1)
                } else if (position(current.x + 1, current.y + 1) == Type.AIR) {
                    // can move diagonal right
                    current = Point(current.x + 1, current.y + 1)
                } else {
                    // cannot move
                    position(current.x, current.y, Type.SAND)
                    return true
                }
            }
        }

        // return true if the sand was placed, false if the sand reach the start
        fun simulateOneSandPart2() : Boolean {
            var current = Point(500, 0)
            var canMove = true
            while (canMove) {
                if (isBottom(current.y + 1)) {
                    position(current.x, current.y, Type.SAND)
                    canMove = false
                } else if (position(current.x, current.y + 1) == Type.AIR) {
                    // can move under
                    current = Point(current.x, current.y + 1)
                } else if (position(current.x - 1, current.y + 1) == Type.AIR) {
                    // can move diagonal left
                    current = Point(current.x - 1, current.y + 1)
                } else if (position(current.x + 1, current.y + 1) == Type.AIR) {
                    // can move diagonal right
                    current = Point(current.x + 1, current.y + 1)
                } else {
                    // cannot move
                    position(current.x, current.y, Type.SAND)
                    canMove = false
                }

                if (current.x == 500 && current.y == 0) {
                    return false
                }
            }
            return true
        }

        fun countSand() = cells.count { it == Type.SAND }
    }

    private fun parse(lines: List<String>) : List<RockLine> {
        return lines.map { line ->
            val points = line.split(" -> ")
            val pointsList = points.map {point ->
                val pointItem = point.split(",")
                Point(pointItem[0].toInt(), pointItem[1].toInt())
            }
            RockLine(pointsList)
        }
    }

    private fun makeGrid(rockLines: List<RockLine>) : Grid {
        val paddingX = 1000
        val maxX = rockLines.flatMap { it.points }.maxOf { it.x } + paddingX
        val minX = rockLines.flatMap { it.points }.minOf { it.x } - paddingX
        val gridWidth = maxX - minX + 1

        val maxY = rockLines.flatMap { it.points }.maxOf { it.y }
        val minY = 0
        val gridHeight = maxY - minY + 1 + 1

        val grid = Grid(gridWidth, gridHeight, offsetX = minX, offsetY = 0)

        rockLines.forEach { grid.fillRockLine(it) }

        return grid
    }

    fun solvePart1(): Int {
        val lines = File(input.toURI()).readLines()
        val rockLines = parse(lines)
        val grid = makeGrid(rockLines)

        var canAddMoreSand = true
        while(canAddMoreSand) {
            canAddMoreSand = grid.simulateOneSand()
        }

        return grid.countSand()
    }

    fun solvePart2(): Int {
        val lines = File(input.toURI()).readLines()
        val rockLines = parse(lines)
        val grid = makeGrid(rockLines)

        var canAddMoreSand = true
        while(canAddMoreSand) {
            canAddMoreSand = grid.simulateOneSandPart2()
            //grid.print()
        }

        return grid.countSand()
    }
}