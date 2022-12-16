package advent

import java.io.File
import java.net.URL
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day15(private val input: URL) {

    data class Point(val x: Int, val y: Int) {
        fun distanceTo(point: Point) = abs(x - point.x) + abs(y - point.y)

        fun tuningFrequency(): Int {
            val result = x * 4000000 + y
            require(result > 0) { "overflow" }
            return result
        }
    }
    data class Sensor(val id: Int, val position: Point, val beacon: Point) {
        private val distance = position.distanceTo(beacon)

        fun isInBeaconExclusionZone(point: Point) : Boolean {
            return point.distanceTo(position) <= distance
        }

        fun rangeX() = ((position.x - distance - 1)..(position.x + distance + 1))
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

    private fun IntRange.combine(other: IntRange) : IntRange {
        return IntRange(min(this.first, other.first), max(this.last, other.last))
    }

    private fun List<Sensor>.isInBeaconExclusionZone(point: Point) : Boolean {
        return this.any { it.isInBeaconExclusionZone(point) }
    }

    private fun List<Sensor>.isNotInBeaconExclusionZone(point: Point) : Boolean {
        return this.none { it.isInBeaconExclusionZone(point) }
    }

    private fun searchPoints(space: Int) : List<Point> {
        return (0..space).flatMap { y ->
            (0..space).map { x -> Point(x, y) }
        }
    }

    fun solvePart1(y: Int): Int {
        val lines = File(input.toURI()).readLines()
        val sensors = lines.mapIndexed{ idx, line -> parse(idx, line) }

        val rangeX = sensors.map { it.rangeX() }.reduce{ a, b -> a.combine(b)}
        val allBeacons = sensors.map { it.beacon }.toSet()

        return rangeX.map { Point(it, y) }
            .filter { !allBeacons.contains(it) }
            .distinct()
            .count { sensors.isInBeaconExclusionZone(it) }
    }

    fun solvePart2(space: Int): Int {
        val lines = File(input.toURI()).readLines()
        val sensors = lines.mapIndexed{ idx, line -> parse(idx, line) }

        val distressBeacon = searchPoints(space)
            .filter { sensors.isNotInBeaconExclusionZone(it) }
        require(distressBeacon.size == 1) { "Found more than one position for distress beacon" }

        return distressBeacon.first().tuningFrequency()
    }
}