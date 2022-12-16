package advent

import org.junit.Test
import kotlin.test.assertEquals

internal class Day12Test {

    private fun underTest() : Day12 {
        val testInput = this.javaClass.getResource("/day12-test-input.txt")!!
        return Day12(testInput)
    }


    @Test
    fun testElevationPriority() {
        assertEquals(0, underTest().elevation('a'))
        assertEquals(15, underTest().elevation('p'))
        assertEquals(25, underTest().elevation('z'))
    }


    @Test
    fun solvePart1() {
        val testInput = this.javaClass.getResource("/day12-test-input.txt")!!
        assertEquals(31, Day12(testInput).solvePart1())
    }

}