package advent

import org.junit.Test
import kotlin.test.assertEquals

internal class Day10Test {

    @Test
    fun solvePart1() {
        val testInput = this.javaClass.getResource("/day10-test-input-big.txt")!!
        assertEquals(13140, Day10(testInput).solvePart1())
    }

    @Test
    fun solvePart2() {
        val testInput = this.javaClass.getResource("/day10-test-input-big.txt")!!
        Day10(testInput).solvePart2()
    }
}