package advent

import java.io.File
import java.net.URL

class Day05(private val input: URL) {

    data class Crate(val char: Char)
    data class Stack(private val crateList: List<Crate>) {
        var crates = crateList.toMutableList()

        fun pop() : Crate {
            val crate = crates.last()
            crates = crates.dropLast(1).toMutableList()
            return crate
        }

        fun push(crate: Crate) {
            crates.add(crate)
        }

        fun top(): Crate {
            return crates.last()
        }
    }

    data class Stacks(val stacks: List<Stack>) {
        fun apply(move: Move) {
            for (i in 0 until move.quantity) {
                val crate = stacks[move.start-1].pop()
                stacks[move.end-1].push(crate)
            }
        }

        fun applyPart2(move: Move) {
            val crates = mutableListOf<Crate>()
            for (i in 0 until move.quantity) {
                crates.add(stacks[move.start-1].pop())
            }

            for (crate in crates.reversed()) {
                stacks[move.end-1].push(crate)
            }
        }

        fun score() : List<Crate> {
            return stacks.map { it.top() }
        }
    }

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
            val stack = stackLines
                .map { it.chunked(4) }
                .filter { it.size >= (stackIdx +1) }
                .map{it[stackIdx]}
                .filter { it.isNotBlank() }

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

        for (move in moves) {
            stacks.apply(move)
        }

        return stacks.score().map { it.char }.joinToString ( separator = "" )
    }

    fun solvePart2(): String {
        val input =  File(input.toURI()).readLines()

        val stacks = extractStacks(input)
        val moves = extractMoves(input)

        for (move in moves) {
            stacks.applyPart2(move)
        }

        return stacks.score().map { it.char }.joinToString ( separator = "" )
    }

}