package advent

import advent.Day09.Position
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class Day09Test {
    private fun underTest() : Day09 {
        val testInput = this.javaClass.getResource("/day09-test-input.txt")!!
        return Day09(testInput)
    }

    @Test
    fun testMoveTailHorizontally() {
        val state = Day09.State(Position(2, 1), Position(1, 1), mutableSetOf(Position(2, 1), Position(1, 1)))

        state.playMove(Day09.Move(Day09.Direction.RIGHT, 1))

        assertEquals(Position(3, 1), state.headPosition)
        assertEquals(Position(2, 1), state.tailPosition)
    }

    @Test
    fun testMoveTailVertically() {
        val state = Day09.State(Position(1, 2), Position(1, 3), mutableSetOf(Position(1, 2), Position(1, 3)))

        state.playMove(Day09.Move(Day09.Direction.DOWN, 1))

        assertEquals(Position(1, 1), state.headPosition)
        assertEquals(Position(1, 2), state.tailPosition)
    }

    @Test
    fun testMoveTailDiagonally1() {
        val state = Day09.State(Position(2, 2), Position(1, 1), mutableSetOf(Position(2, 2), Position(1, 1)))

        state.playMove(Day09.Move(Day09.Direction.UP, 1))

        assertEquals(Position(2, 3), state.headPosition)
        assertEquals(Position(2, 2), state.tailPosition)
    }

    @Test
    fun testMoveTailDiagonally2() {
        val state = Day09.State(Position(2, 2), Position(1, 1), mutableSetOf(Position(2, 2), Position(1, 1)))

        state.playMove(Day09.Move(Day09.Direction.RIGHT, 1))

        assertEquals(Position(3, 2), state.headPosition)
        assertEquals(Position(2, 2), state.tailPosition)
    }

    @Test
    fun solvePart1() {
        assertEquals(13, underTest().solvePart1())
    }
}