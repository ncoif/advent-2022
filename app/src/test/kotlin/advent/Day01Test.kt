package advent

import org.junit.Test
import kotlin.test.assertEquals

internal class Day01Test {
    @Test
    fun solvePart1() {
        val testInput = this.javaClass.getResource("/day01-test-input.txt")!!
        assertEquals(24000, Day01(testInput).solvePart1())
    }

    @Test
    fun solvePart2() {
        val testInput = this.javaClass.getResource("/day01-test-input.txt")!!
        assertEquals(45000, Day01(testInput).solvePart2())
    }
}