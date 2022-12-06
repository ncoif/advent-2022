package advent

import java.io.File
import java.net.URL

class Day06(private val input: URL) {

    fun marker(message: String) : Int {
        val chars = message.toCharArray().toList()
        var index = 4
        for (window in chars.windowed(4)) {
            if (window.distinct().size != 4) {
                index ++
            } else {
                break
            }
        }
        return index
    }

    fun markerMessage(message: String) : Int {
        val chars = message.toCharArray().toList()
        var index = 14
        for (window in chars.windowed(14)) {
            if (window.distinct().size != 14) {
                index ++
            } else {
                break
            }
        }
        return index
    }

    fun solvePart1(): Int {
        val input = File(input.toURI()).readLines().first()
        return marker(input)
    }

    fun solvePart2(): Int {
        val input = File(input.toURI()).readLines().first()
        return markerMessage(input)
    }


}