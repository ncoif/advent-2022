package advent

import java.io.File

class Day01(val inputFileName: String) {

    fun solve(): Int {
        val lines = File(inputFileName).readLines()

        var maxElf = 0
        var currentElf = 0
        for (line in lines) {
            if (line != "") {
                currentElf += line.toInt()
            } else {
                currentElf = 0
            }

            if (maxElf < currentElf) {
                maxElf = currentElf
            }
        }

        return maxElf
    }
}