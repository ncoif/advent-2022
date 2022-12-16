package advent

import org.junit.Test
import kotlin.test.assertEquals

internal class Day14Test {

    private fun underTest() : Day14 {
        val testInput = this.javaClass.getResource("/day14-test-input.txt")!!
        return Day14(testInput)
    }

    @Test
    fun solvePart1() {
        assertEquals(24, underTest().solvePart1())
    }
}