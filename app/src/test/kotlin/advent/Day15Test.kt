package advent

import advent.Day15.Point
import advent.Day15.Sensor
import org.junit.Test
import kotlin.test.assertEquals

internal class Day15Test {

    private fun underTest() : Day15 {
        val testInput = this.javaClass.getResource("/day15-test-input.txt")!!
        return Day15(testInput)
    }

    @Test
    fun testDistance() {
        assertEquals(0, Point(0, 0).distanceTo(Point(0, 0)))
        assertEquals(1, Point(0, 0).distanceTo(Point(1, 0)))
        assertEquals(1, Point(0, 0).distanceTo(Point(0, 1)))
        assertEquals(2, Point(0, 0).distanceTo(Point(1, 1)))
    }

    @Test
    fun testExclusionZone() {
        val sensor1 = Sensor(0, Point(0, 0), Point(1, 0))
        assertEquals(5, sensor1.beaconExclusionZone().size)

        val sensor2 = Sensor(0, Point(0, 0), Point(2, 0))
        assertEquals(13, sensor2.beaconExclusionZone().size)

        val sensor3 = Sensor(0, Point(0, 0), Point(3, 0))
        assertEquals(25, sensor3.beaconExclusionZone().size)
    }

    @Test
    fun solvePart1() {
        assertEquals(26, underTest().solvePart1(y = 10))
    }
}