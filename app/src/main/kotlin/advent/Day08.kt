package advent

import java.io.File
import java.net.URL

class Day08(private val input: URL) {

    data class Tree(val height: Int)
    data class Grid(private val width: Int, private val height: Int, val trees: List<Tree>) {
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
            return neighbours.all { it.height < tree.height }
        }

        fun scenicScore(w: Int, h: Int) : Int {
            val viewingDistanceTop = viewingDistance(position(w, h), treesOnTop(w, h).reversed())
            val viewingDistanceBottom = viewingDistance(position(w, h), treesOnBottom(w, h))
            val viewingDistanceLeft = viewingDistance(position(w, h), treesOnLeft(w, h).reversed())
            val viewingDistanceRight = viewingDistance(position(w, h), treesOnRight(w, h))

            return viewingDistanceTop * viewingDistanceBottom * viewingDistanceLeft * viewingDistanceRight
        }

        private fun viewingDistance(tree: Tree, neighbours: List<Tree>) : Int {
            var viewingDistance = 0
            for (neighbour in neighbours) {
                viewingDistance += 1
                if (neighbour.height >= tree.height) {
                    break // view is blocked, stop counting
                }
            }
            return viewingDistance
        }

        fun allPosition(): List<Pair<Int, Int>> {
            return (0 until height).flatMap { w ->
                (0 until width).map { Pair(w, it) }
            }
        }
    }

    fun parseGrid(lines: List<String>) : Grid {
        val trees = lines.flatMap {
            line -> line.toCharArray().map{ height -> Tree(height.digitToInt()) }
        }

        return Grid(lines[0].length, lines.size, trees)
    }

    fun solvePart1(): Int {
        val grid = parseGrid(File(input.toURI()).readLines())

        return grid.allPosition()
            .count { grid.isVisibleFromOutside(it.first, it.second) }
    }

    fun solvePart2(): Int {
        val grid = parseGrid(File(input.toURI()).readLines())

        return grid.allPosition()
            .maxOf { grid.scenicScore(it.first, it.second) }
    }
}