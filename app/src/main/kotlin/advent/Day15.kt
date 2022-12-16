package advent

import java.io.File
import java.net.URL
import kotlin.math.abs

class Day15(private val input: URL) {

    data class Point(val x: Int, val y: Int) {
        fun distanceTo(point: Point) = abs(x - point.x) + abs(y - point.y)
    }
    data class Sensor(val id: Int, val position: Point, val beacon: Point) {
        private val distance = position.distanceTo(beacon)

        // return the list of points in which a beacon cannot be present
        fun beaconExclusionZone() : List<Point> {
            return pointsToScan()
                .filter { p -> p.distanceTo(position) <= distance }
        }

        private fun pointsToScan() : List<Point> {
            return  ((position.y - distance - 1)..(position.y + distance + 1)).flatMap { y ->
                ((position.x - distance - 1)..(position.x + distance + 1)).map { x -> Point(x, y)}
            }
        }
    }

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
        val allBeacons = sensors.map { it.beacon }.toSet()
        val tmp = sensors[6].beaconExclusionZone()

        return sensors.flatMap { it.beaconExclusionZone() }
            .distinct()
            .filter { !allBeacons.contains(it) }
            .count { it.y == y }
    }
}