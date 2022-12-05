package advent

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class Day04Test {
    private fun underTest() : Day04 {
        val testInput = this.javaClass.getResource("/day04-test-input.txt")!!
        return Day04(testInput)
    }

    @Test
    fun testContains() {
        assertFalse {
            Day04.Assignment(2, 4).contains(Day04.Assignment(6, 8))
        }
        assertFalse {
            Day04.Assignment(5, 7).contains(Day04.Assignment(7, 9))
        }
        assertTrue {
            Day04.Assignment(2, 8).contains(Day04.Assignment(3, 7))
        }
        assertTrue {
            Day04.Assignment(3, 7).contains(Day04.Assignment(2, 8))
        }

        assertTrue {
            Day04.Assignment(6, 6).contains(Day04.Assignment(4, 6))
        }
        assertTrue {
            Day04.Assignment(4, 6).contains(Day04.Assignment(6, 6))
        }
    }

    @Test
    fun solvePart1() {
        assertEquals(2, underTest().solvePart1())
    }

    @Test
    fun testOverlap() {
        assertFalse {
            Day04.Assignment(2, 4).overlap(Day04.Assignment(6, 8))
        }
        assertTrue {
            Day04.Assignment(5, 7).overlap(Day04.Assignment(7, 9))
        }
        assertTrue {
            Day04.Assignment(7, 9).overlap(Day04.Assignment(5, 7))
        }
        assertTrue {
            Day04.Assignment(2, 8).overlap(Day04.Assignment(3, 7))
        }
        assertTrue {
            Day04.Assignment(3, 7).overlap(Day04.Assignment(2, 8))
        }
        assertTrue {
            Day04.Assignment(6, 6).overlap(Day04.Assignment(4, 6))
        }
        assertTrue {
            Day04.Assignment(4, 6).overlap(Day04.Assignment(6, 6))
        }
    }


    @Test
    fun solvePart2() {
        assertEquals(4, underTest().solvePart2())
    }

}