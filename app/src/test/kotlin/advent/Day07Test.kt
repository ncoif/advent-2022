package advent

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class Day07Test {
    private fun underTest() : Day07 {
        val testInput = this.javaClass.getResource("/day07-test-input.txt")!!
        return Day07(testInput)
    }

    @Test
    fun testParseFileSystem() {
        val testInput = this.javaClass.getResource("/day07-test-input.txt")!!
        val fileSystem = underTest().parseFileSystem(File(testInput.toURI()).readLines())

        assertTrue(fileSystem is Day07.Dir)
        assertEquals(4, fileSystem.files.size)
        assertEquals(48381165, fileSystem.size())

        val dirA = fileSystem.files[0]
        assertTrue(dirA is Day07.Dir)
        assertEquals("a", dirA.name)
        assertEquals(4, dirA.files.size)
        assertEquals(94853, dirA.size())

        val dirE = dirA.files[0]
        assertTrue(dirE is Day07.Dir)
        assertEquals("e", dirE.name)
        assertEquals(1, dirE.files.size)
        assertEquals(584, dirE.size())

        val fileI = dirE.files[0]
        assertTrue(fileI is Day07.File)
        assertEquals("i", fileI.name)
        assertEquals(584, fileI.size)
    }

    @Test
    fun testPrettyPrint() {
        val testInput = this.javaClass.getResource("/day07-test-input.txt")!!
        val fileSystem = underTest().parseFileSystem(File(testInput.toURI()).readLines())
        println(fileSystem.prettyString())
    }

    @Test
    fun solvePart1() {
        assertEquals(95437, underTest().solvePart1())
    }

    @Test
    fun solvePart2() {
        assertEquals(24933642, underTest().solvePart2())
    }
}