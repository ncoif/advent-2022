package advent

import edu.princeton.cs.algorithms.DirectedEdge
import edu.princeton.cs.algorithms.EdgeWeightedDigraph
import java.io.File
import java.net.URL
import kotlin.math.abs

class Day12(private val input: URL) {

    data class Elevation(val index: Int, val height: Int, val isStart: Boolean = false, val isEnd: Boolean = false)
    data class Grid(private val width: Int, private val height: Int, private val elevations: List<Elevation>) {

        private fun position(w: Int, h: Int) = (elevations[w + h * width])

        private fun neighbours(w: Int, h: Int): List<Elevation> {
            val neighbours = mutableListOf<Elevation>()
            if (w > 0 && h > 0 ) {
                neighbours.add(position(w - 1, h - 1))
            }
            if (w > 0 && h < height - 1) {
                neighbours.add(position(w - 1, h + 1))
            }
            if (w < width - 1 && h > 0) {
                neighbours.add(position(w + 1, h - 1))
            }
            if (w < width - 1 && h < height - 1) {
                neighbours.add(position(w + 1, h + 1))
            }
            return neighbours
        }

        fun toGraph(): EdgeWeightedDigraph {
            val graph = EdgeWeightedDigraph(elevations.size)
            for (w in 0 until width) {
                for (h in 0 until height) {
                    val current = position(w, h)
                    val neighbours = neighbours(w, h)
                    val connectedNeighbours = neighbours.filter { abs(it.height - current.height) == 1 }
                    for (neighbour in connectedNeighbours) {
                        val edge: DirectedEdge = DirectedEdge(current.index, neighbour.index, 1.0)
                        graph.addEdge(edge)
                    }
                }
            }
            return graph
        }
    }

    fun elevation(type: Char) = (type.code - 97)

    private fun parse(idx: Int, char: Char): Elevation {
        return when (char) {
            'S' -> Elevation(idx, 0, isStart = true, isEnd = false)
            'E' -> Elevation(idx, 25, isStart = false, isEnd = true)
            else -> Elevation(idx, elevation(char))
        }
    }

    private fun parse(lines: List<String>): Grid {
        val elevations = mutableListOf<Elevation>()
        var idx = 0
        for (line in lines) {
            for (char in line.toCharArray()) {
                elevations.add(idx, parse(idx, char))
                idx ++
            }
        }

        return Grid(lines[0].length, lines.size, elevations)
    }

    fun solvePart1(): Long {
        val lines = File(input.toURI()).readLines()
        val grid = parse(lines)
        val graph = grid.toGraph()

        return 0
    }
}