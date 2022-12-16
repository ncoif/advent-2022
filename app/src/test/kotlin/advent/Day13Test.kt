package advent

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class Day13Test {

    private fun underTest() : Day13 {
        val testInput = this.javaClass.getResource("/day13-test-input.txt")!!
        return Day13(testInput)
    }

    @Test
    fun testPair1() {
        val first = "[1,1,3,1,1]"
        val second = "[1,1,5,1,1]"
        assertTrue { underTest().comparePair(first, second) }
        assertFalse { underTest().comparePair(second, first) }
    }

    @Test
    fun testPair2() {
        val first = "[[1],[2,3,4]]"
        val second = "[[1],4]"
        assertTrue { underTest().comparePair(first, second) }
        assertFalse { underTest().comparePair(second, first) }
    }

    @Test
    fun testPair3() {
        val first = "[9]"
        val second = "[[8,7,6]]"
        assertFalse { underTest().comparePair(first, second) }
        assertTrue { underTest().comparePair(second, first) }
    }

    @Test
    fun testPair4() {
        val first = "[[4,4],4,4]"
        val second = "[[4,4],4,4,4]"
        assertTrue { underTest().comparePair(first, second) }
        assertFalse { underTest().comparePair(second, first) }
    }

    @Test
    fun testPair5() {
        val first = "[7,7,7,7]"
        val second = "[7,7,7]"
        assertFalse { underTest().comparePair(first, second) }
        assertTrue { underTest().comparePair(second, first) }
    }

    @Test
    fun testPair6() {
        val first = "[]"
        val second = "[3]"
        assertTrue { underTest().comparePair(first, second) }
        assertFalse { underTest().comparePair(second, first) }
    }

    @Test
    fun testPair7() {
        val first = "[[[]]]"
        val second = "[[]]"
        assertFalse { underTest().comparePair(first, second) }
        assertTrue { underTest().comparePair(second, first) }
    }

    @Test
    fun testPair8() {
        val first = "[1,[2,[3,[4,[5,6,7]]]],8,9]"
        val second = "[1,[2,[3,[4,[5,6,0]]]],8,9]"
        assertFalse { underTest().comparePair(first, second) }
        assertTrue { underTest().comparePair(second, first) }
    }

    @Test
    fun solvePart1() {
        val testInput = this.javaClass.getResource("/day13-test-input.txt")!!
        assertEquals(13, Day13(testInput).solvePart1())
    }

    @Test
    fun solvePart2() {
        val testInput = this.javaClass.getResource("/day13-test-input.txt")!!
        assertEquals(140, Day13(testInput).solvePart2())
    }

}