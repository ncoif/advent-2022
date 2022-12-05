package advent

import org.junit.Test

internal class Day01Test {
    @Test
    fun readTestInput() {
        val day01 = Day01("")
        val fileName = this.javaClass.getResource("/day01-test-input.txt")?.file!!
        day01.readInput(fileName)
    }
}