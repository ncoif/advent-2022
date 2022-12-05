package advent

import org.junit.Test
import kotlin.test.assertEquals

internal class Day01Test {
    @Test
    fun readTestInput() {
        val fileName = this.javaClass.getResource("/day01-test-input.txt")?.file!!
        assertEquals(24000, Day01(fileName).solve())
    }
}