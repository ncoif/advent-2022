package advent

import advent.Day09.Position
import org.junit.Test
import kotlin.test.assertEquals

internal class Day09Test {

    @Test
    fun testMoveTailHorizontally() {
        val state = Day09.State(Position(2, 1), 1, arrayOf(Position(1, 1)), mutableSetOf(Position(2, 1), Position(1, 1)))

        state.playMove(Day09.Move(Day09.Direction.RIGHT, 1))

        assertEquals(Position(3, 1), state.headPosition)
        assertEquals(Position(2, 1), state.tailPosition[0])
    }

    @Test
    fun testMoveTailVertically() {
        val state = Day09.State(Position(1, 2), 1, arrayOf(Position(1, 3)), mutableSetOf(Position(1, 2), Position(1, 3)))

        state.playMove(Day09.Move(Day09.Direction.DOWN, 1))

        assertEquals(Position(1, 1), state.headPosition)
        assertEquals(Position(1, 2), state.tailPosition[0])
    }

    @Test
    fun testMoveTailDiagonally1() {
        val state = Day09.State(Position(2, 2), 1, arrayOf(Position(1, 1)), mutableSetOf(Position(2, 2), Position(1, 1)))

        state.playMove(Day09.Move(Day09.Direction.UP, 1))

        assertEquals(Position(2, 3), state.headPosition)
        assertEquals(Position(2, 2), state.tailPosition[0])
    }

    @Test
    fun testMoveTailDiagonally2() {
        val state = Day09.State(Position(2, 2),1, arrayOf(Position(1, 1)), mutableSetOf(Position(2, 2), Position(1, 1)))

        state.playMove(Day09.Move(Day09.Direction.RIGHT, 1))

        assertEquals(Position(3, 2), state.headPosition)
        assertEquals(Position(2, 2), state.tailPosition[0])
    }

    @Test
    fun solvePart1() {
        val testInput = this.javaClass.getResource("/day09-test-input.txt")!!
        assertEquals(13, Day09(testInput).solvePart1())
    }

    @Test
    fun solvePart2() {
        val testInput = this.javaClass.getResource("/day09-test-input-2.txt")!!
        assertEquals(36, Day09(testInput).solvePart2())
    }
}