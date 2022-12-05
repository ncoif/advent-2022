package advent

import org.junit.Test
import kotlin.test.assertEquals

internal class Day01Test {
    @Test
    fun readTestInput() {
        val testInput = this.javaClass.getResource("/day01-test-input.txt")!!
        assertEquals(24000, Day01(testInput).solve())
    }
}