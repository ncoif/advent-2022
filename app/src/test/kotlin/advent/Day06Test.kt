package advent

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

internal class Day06Test {
    private fun underTest() : Day06 {
        val testInput = this.javaClass.getResource("/day06-test-input.txt")!!
        return Day06(testInput)
    }

    @Test
    fun testMarker() {
        assertEquals(7, underTest().marker("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
        assertEquals(5, underTest().marker("bvwbjplbgvbhsrlpgdmjqwftvncz"))
        assertEquals(6, underTest().marker("nppdvjthqldpwncqszvftbrmjlhg"))
        assertEquals(10, underTest().marker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
        assertEquals(11, underTest().marker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
    }

    @Test
    fun solvePart1() {
        assertEquals(7, underTest().solvePart1())
    }

}