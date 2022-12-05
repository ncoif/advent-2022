package advent

import org.junit.Test
import kotlin.test.assertEquals

internal class Day02Test {
    @Test
    fun solvePart1() {
        val testInput = this.javaClass.getResource("/day02-test-input.txt")!!
        assertEquals(15, Day02(testInput).solvePart1())
    }
}