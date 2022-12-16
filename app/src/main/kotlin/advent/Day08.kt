package advent

import java.io.File
import java.net.URL

class Day08(private val input: URL) {

    data class Tree(val height: Int)
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
            return (0 until w).map { position(it, h) }
        }

        fun treesOnRight(w: Int, h: Int): List<Tree> {
            return (w + 1 until width).map { position(it, h) }
        }

        fun isVisibleFromOutside(w: Int, h:Int) : Boolean {
            return isVisible(position(w, h), treesOnTop(w, h))
                    || isVisible(position(w, h), treesOnBottom(w, h))
                    || isVisible(position(w, h), treesOnLeft(w, h))
                    || isVisible(position(w, h), treesOnRight(w, h))
        }

        private fun isVisible(tree: Tree, neighbours: List<Tree>) : Boolean {
            var visible = true
            for (neighbour in neighbours) {
                if (neighbour.height >= tree.height) {
                    visible = false
                    break
                }
            }
            return visible
        }
    }

    fun parseGrid(lines: List<String>) : Grid {
        val trees = lines.flatMap {
            it.toCharArray().map{ height -> Tree(height.digitToInt()) }
        }

        return Grid(lines[0].length, lines.size, trees)
    }

    fun solvePart1(): Int {
        val grid = parseGrid(File(input.toURI()).readLines())

        var visibleCount = 0
        for (w in 0 until grid.width) {
            for (h in 0 until grid.height) {
                if (grid.isVisibleFromOutside(w, h)) {
                    visibleCount += 1
                }
            }
        }

        return visibleCount
    }
}