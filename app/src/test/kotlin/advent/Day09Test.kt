package advent

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class Day09Test {
    private fun underTest() : Day09 {
        val testInput = this.javaClass.getResource("/day09-test-input.txt")!!
        return Day09(testInput)
    }

    @Test
    fun solvePart1() {
        assertEquals(13, underTest().solvePart1())
    }
}