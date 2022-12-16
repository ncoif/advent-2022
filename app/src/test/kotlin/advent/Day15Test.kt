package advent

import org.junit.Test
import kotlin.test.assertEquals

internal class Day15Test {

    private fun underTest() : Day15 {
        val testInput = this.javaClass.getResource("/day15-test-input.txt")!!
        return Day15(testInput)
    }

    @Test
    fun solvePart1() {
        assertEquals(26, underTest().solvePart1(y = 10))
    }
}