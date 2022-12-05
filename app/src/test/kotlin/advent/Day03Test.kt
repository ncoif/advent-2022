package advent

import org.junit.Test
import kotlin.test.assertEquals

internal class Day03Test {
    private fun underTest() : Day03 {
        val testInput = this.javaClass.getResource("/day03-test-input.txt")!!
        return Day03(testInput)
    }

    @Test
    fun testPriority() {
        assertEquals(16, underTest().priority('p'))
        assertEquals(38, underTest().priority('L'))
    }

    @Test
    fun solvePart1() {
        assertEquals(157, underTest().solvePart1())
    }

    @Test
    fun solvePart2() {
        assertEquals(70, underTest().solvePart2())
    }
}