package advent

import java.io.File
import java.net.URL

class Day15(private val input: URL) {

    data class Point(val x: Int, val y: Int)
    data class Sensor(val id: Int, val position: Point, val beacon: Point)

    private fun parse(coordinate: String) = coordinate.split("=")[1].toInt()

    private fun parse(lineIdx: Int, line: String) : Sensor {
        // Sensor at x=2, y=18: closest beacon is at x=-2, y=15
        val lineItem = line
            .replace(",", "")
            .replace(":", "")
            .split(" ")
        val positionX = parse(lineItem[2])
        val positionY = parse(lineItem[3])

        val beaconX = parse(lineItem[8])
        val beaconY = parse(lineItem[9])

        return Sensor(lineIdx, Point(positionX, positionY), Point(beaconX, beaconY))
    }

    fun solvePart1(y: Int): Int {
        val lines = File(input.toURI()).readLines()
        val sensors = lines.mapIndexed{ idx, line -> parse(idx, line) }

        return 0
    }
}