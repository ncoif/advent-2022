package advent

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

internal class Day08Test {
    private fun underTest() : Day08 {
        val testInput = this.javaClass.getResource("/day08-test-input.txt")!!
        return Day08(testInput)
    }

    @Test
    fun testParseGrid() {
        val testInput = this.javaClass.getResource("/day08-test-input.txt")!!
        val grid = underTest().parseGrid(File(testInput.toURI()).readLines())
        assertEquals(Day08.Tree(3, false), grid.trees[0])
        assertEquals(Day08.Tree(0, false), grid.trees[1])
        assertEquals(Day08.Tree(3, false), grid.trees[2])
        assertEquals(Day08.Tree(7, false), grid.trees[3])
        assertEquals(Day08.Tree(3, false), grid.trees[4])
        assertEquals(Day08.Tree(2, false), grid.trees[5])
    }

    @Test
    fun testGripPosition() {
        val testInput = this.javaClass.getResource("/day08-test-input.txt")!!
        val grid = underTest().parseGrid(File(testInput.toURI()).readLines())

        assertEquals(Day08.Tree(3, false), grid.position(0, 0))
        assertEquals(Day08.Tree(3, false), grid.position(0, 4))
        assertEquals(Day08.Tree(3, false), grid.position(2, 2))
        assertEquals(Day08.Tree(3, false), grid.position(4, 0))
        assertEquals(Day08.Tree(0, false), grid.position(4, 4))

        assertEquals(Day08.Tree(5, false), grid.position(1, 1))
        assertEquals(Day08.Tree(4, false), grid.position(3, 3))
    }

    @Test
    fun solvePart1() {
        assertEquals(21, underTest().solvePart1())
    }
}