package advent

import edu.princeton.cs.algorithms.DijkstraSP
import edu.princeton.cs.algorithms.DirectedEdge
import edu.princeton.cs.algorithms.EdgeWeightedDigraph
import java.io.File
import java.net.URL

class Day12(private val input: URL) {

    data class Elevation(val index: Int, val height: Int, val isStart: Boolean = false, val isEnd: Boolean = false)
    data class Grid(private val width: Int, private val height: Int, private val elevations: List<Elevation>) {

        private fun position(w: Int, h: Int) = (elevations[w + h * width])

        fun start() = (elevations.first { it.isStart })
        fun end() = (elevations.first { it.isEnd })

        private fun neighbours(w: Int, h: Int): List<Elevation> {
            val neighbours = mutableListOf<Elevation>()
            if (w > 0) {
                neighbours.add(position(w - 1, h))
            }
            if (w < width - 1) {
                neighbours.add(position(w + 1, h))
            }
            if (h > 0) {
                neighbours.add(position(w, h - 1))
            }
            if (h < height - 1) {
                neighbours.add(position(w, h + 1))
            }
            return neighbours
        }

        fun toGraph(): EdgeWeightedDigraph {
            val graph = EdgeWeightedDigraph(elevations.size)
            for (h in 0 until height) {
                for (w in 0 until width) {
                    val current = position(w, h)
                    val neighbours = neighbours(w, h)
                    val connectedNeighbours = neighbours.filter { (it.height - current.height) <= 1 }
                    for (neighbour in connectedNeighbours) {
                        val edge = DirectedEdge(current.index, neighbour.index, 1.0)
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

    fun solvePart1(): Int {
        val lines = File(input.toURI()).readLines()
        val grid = parse(lines)

        val graph = grid.toGraph()

        val dijkstra = DijkstraSP(graph, grid.start().index)

        val path = dijkstra.pathTo(grid.end().index)
        val pathList = path.iterator().asSequence().toList()

        return pathList.size
    }
}