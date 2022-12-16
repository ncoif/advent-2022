package advent

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

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
    fun testNeighbourTrees() {
        val testInput = this.javaClass.getResource("/day08-test-input.txt")!!
        val grid = underTest().parseGrid(File(testInput.toURI()).readLines())

        val actualTop = grid.treesOnTop(1, 2)
        assertEquals(2, actualTop.size)
        assertEquals(Day08.Tree(0, false), actualTop[0])
        assertEquals(Day08.Tree(5, false), actualTop[1])

        val actualBottom = grid.treesOnBottom(1, 2)
        assertEquals(2, actualBottom.size)
        assertEquals(Day08.Tree(3, false), actualBottom[0])
        assertEquals(Day08.Tree(5, false), actualBottom[1])

        val actualLeft = grid.treesOnLeft(2, 2)
        assertEquals(2, actualLeft.size)
        assertEquals(Day08.Tree(6, false), actualLeft[0])
        assertEquals(Day08.Tree(5, false), actualLeft[1])

        val actualRight = grid.treesOnRight(2, 2)
        assertEquals(2, actualRight.size)
        assertEquals(Day08.Tree(3, false), actualRight[0])
        assertEquals(Day08.Tree(2, false), actualRight[1])

        val actualEdgeRight = grid.treesOnRight(4, 4)
        assertTrue { actualEdgeRight.isEmpty() }

        val actualEdgeBottom = grid.treesOnRight(4, 4)
        assertTrue { actualEdgeBottom.isEmpty() }
    }

    @Test
    fun solvePart1() {
        assertEquals(21, underTest().solvePart1())
    }
}