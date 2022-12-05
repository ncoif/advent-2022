package advent

import java.io.File
import java.net.URL

class Day05(private val input: URL) {

    data class Crate(val char: Char)
    data class Stack(val crates: List<Crate>)
    data class Stacks(val stacks: List<Stack>)

    data class Move(val quantity: Int, val start: Int, val end: Int)

    private fun emptyLine(lines: List<String>): Int {
        for ((i, line) in lines.withIndex()) {
            if (line.isEmpty()) {
                return i;
            }
        }
        throw IllegalArgumentException("No empty line")
    }

    fun extractStacks(lines: List<String>): Stacks {
        val emptyLine = emptyLine(lines)
        val stackLines = lines.subList(0, emptyLine - 1)

        // "[Z] [M] [P]"
        val stackNumber = stackLines[stackLines.size - 1].split(" ").size

        val stacks = mutableListOf<Stack>()
        for (stackIdx in 0 until stackNumber) {
            val stack = stackLines.map { it.chunked(4) }.map{it[stackIdx]}.filter { it.isNotBlank() }

            stacks.add(Stack(stack.map { Crate(it.toCharArray()[1]) }.reversed()))
        }

        return Stacks(stacks)
    }

    fun extractMoves(lines: List<String>): List<Move> {
        val emptyLine = emptyLine(lines)
        val moves = lines.subList(emptyLine + 1, lines.size)

        return moves.map {
            // "move 1 from 2 to 1"
            val words = it.split(" ")
            Move(words[1].toInt(), words[3].toInt(), words[5].toInt())
        }
    }

    fun solvePart1(): String {
        val input =  File(input.toURI()).readLines()

        val stacks = extractStacks(input)
        val moves = extractMoves(input)


        return ""
    }
}