package advent

import java.io.File
import java.net.URL

class Day12(private val input: URL) {

    data class Elevation(val height: Int, val isStart: Boolean = false, val isEnd: Boolean = false)
    data class Grid(private val width: Int, private val height: Int, private val elevation: List<Elevation>) {
        fun position(w: Int, h: Int): Elevation {
            return elevation[w + h * width]
        }
    }

    fun elevation(type: Char) = (type.code - 97)

    private fun parse(char: Char): Elevation {
        return when (char) {
            'S' -> Elevation(0, isStart = true, isEnd = false)
            'E' -> Elevation(25, isStart = false, isEnd = true)
            else -> Elevation(elevation(char))
        }
    }

    private fun parse(lines: List<String>): Grid {
        val elevations = lines.flatMap {
                line -> line.toCharArray().map{ parse(it) }
        }

        return Grid(lines[0].length, lines.size, elevations)
    }

    fun solvePart1(): Long {
        val lines = File(input.toURI()).readLines()
        val grid = parse(lines)

        return 0
    }
}