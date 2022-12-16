package advent

import advent.Day15.Point
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
    fun solvePart1() {
        assertEquals(26, underTest().solvePart1(y = 10))
    }

    @Test
    fun solvePart2() {
        assertEquals(56000011, underTest().solvePart2(space = 20))
    }

}