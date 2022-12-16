package advent

import java.io.File
import java.net.URL

class Day08(private val input: URL) {

    data class Tree(val height: Int, val visible: Boolean)
    data class Grid(val width: Int, val height: Int, val trees: List<Tree>) {
        fun position(w: Int, h: Int) : Tree {
            return trees[w + h * width]
        }

        fun treesOnTop(w: Int, h: Int): List<Tree> {
            return (0 until h).map { position(w, it) }
        }

        fun treesOnBottom(w: Int, h: Int): List<Tree> {
            return (h + 1 until height).map { position(w, it) }
        }

        fun treesOnLeft(w: Int, h: Int): List<Tree> {
            return (0 until w).map { position(it, w) }
        }

        fun treesOnRight(w: Int, h: Int): List<Tree> {
            return (w + 1 until width).map { position(it, w) }
        }

    }

    fun parseGrid(lines: List<String>) : Grid {
        val trees = lines.flatMap {
            it.toCharArray().map{ height -> Tree(height.digitToInt(), false) }
        }

        return Grid(lines[0].length, lines.size, trees)
    }

    fun updateVisibility(grid: Grid) {

    }

    fun solvePart1(): Long {
        val input = File(input.toURI()).readLines()
        return 0
    }
}