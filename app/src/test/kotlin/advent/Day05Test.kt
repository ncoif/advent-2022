package advent

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

internal class Day05Test {
    private fun underTest() : Day05 {
        val testInput = this.javaClass.getResource("/day05-test-input.txt")!!
        return Day05(testInput)
    }

    @Test
    fun extractMoves() {
        val testInput = this.javaClass.getResource("/day05-test-input.txt")!!
        val moves = underTest().extractMoves(File(testInput.toURI()).readLines())
        assertEquals(4, moves.size)
    }

    @Test
    fun extractStacks() {
        val testInput = this.javaClass.getResource("/day05-test-input.txt")!!
        val stacks = underTest().extractStacks(File(testInput.toURI()).readLines())
        assertEquals(3, stacks.stacks.size)

        assertEquals(2, stacks.stacks[0].crates.size)
        assertEquals(3, stacks.stacks[1].crates.size)
        assertEquals(1, stacks.stacks[2].crates.size)
    }

    @Test
    fun solvePart1() {
        assertEquals("CMZ", underTest().solvePart1())
    }

    @Test
    fun solvePart2() {
        assertEquals("MCD", underTest().solvePart2())
    }

}