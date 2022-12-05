package advent

import java.io.File
import java.net.URL

class Day01(private val input: URL) {

    fun solvePart1(): Int {
        val lines = File(input.toURI()).readLines()

        var maxElf = 0
        var currentElf = 0
        for (line in lines) {
            currentElf = if (line != "") (currentElf + line.toInt()) else 0
            maxElf = maxOf(maxElf, currentElf)
        }

        return maxElf
    }

    fun solvePart2() : Int {
        val lines = File(input.toURI()).readLines()

        val allElf : ArrayList<Int> = arrayListOf()

        var currentElf = 0
        for (line in lines) {
            if (line != "") {
                currentElf += line.toInt()
            } else {
                allElf.add(currentElf)
                currentElf = 0
            }
        }
        allElf.add(currentElf)

        return allElf.sortedDescending().take(3).sum()
    }
}