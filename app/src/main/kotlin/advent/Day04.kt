package advent

import java.io.File
import java.lang.IllegalStateException
import java.net.URL

class Day04(private val input: URL) {

    data class Assignment(val start: Int, val end: Int) {
        fun overlap(other: Assignment) : Boolean {
            val otherContained = start <= other.start && other.end <= end
            val thisContained = other.start <= start && end <= other.end
            return otherContained || thisContained
        }
    }
    data class Pair(val first: Assignment, val second: Assignment)

    private fun extract(line: String) : Pair {
        val pairStrings = line.split(",")

        val assignments = pairStrings.map { extractAssignment(it) }
        if (assignments.size != 2) {
            throw IllegalArgumentException()
        }

        return Pair(assignments[0], assignments[1])
    }

    private fun extractAssignment(pair: String): Assignment {
        val splits = pair.split("-")

        if (splits.size != 2) {
            throw IllegalArgumentException()
        }
        return Assignment(splits[0].toInt(), splits[1].toInt())
    }

    fun solvePart1(): Int {
        return File(input.toURI()).readLines()
            .map { extract(it) }
            .count { it.first.overlap(it.second) }

    }
}