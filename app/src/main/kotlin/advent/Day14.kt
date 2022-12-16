package advent

import java.io.File
import java.net.URL

class Day14(private val input: URL) {

    enum class Type { ROCK, AIR, SAND }
    data class Point(val x: Int, val y: Int)
    data class RockLine(val points: List<Point>)

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

    fun solvePart1(): Int {
        val lines = File(input.toURI()).readLines()
        println(parse(lines))
        return 0
    }
}