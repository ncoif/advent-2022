package advent

import java.io.File
import java.net.URL

class Day01(private val input: URL) {

    fun solve(): Int {
        val lines = File(input.toURI()).readLines()

        var maxElf = 0
        var currentElf = 0
        for (line in lines) {
            currentElf = if (line != "") (currentElf + line.toInt()) else 0
            maxElf = maxOf(maxElf, currentElf)
        }

        return maxElf
    }
}