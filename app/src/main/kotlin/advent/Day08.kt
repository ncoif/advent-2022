package advent

import java.io.File
import java.net.URL

class Day08(private val input: URL) {

    data class Tree(val height: Int, val visible: Boolean)
    data class Grid(private val width: Int, private val height: Int, val trees: List<Tree>) {
        fun position(w: Int, h: Int) : Tree {
            return trees[w + h * width]
        }
    }

    fun parseGrid(lines: List<String>) : Grid {
        val trees = lines.flatMap {
            it.toCharArray().map{ height -> Tree(height.digitToInt(), false) }
        }

        return Grid(lines[0].length, lines.size, trees)
    }

    fun solvePart1(): Long {
        val input = File(input.toURI()).readLines()
        return 0
    }
}