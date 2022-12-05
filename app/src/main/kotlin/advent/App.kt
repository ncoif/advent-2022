package advent

import java.net.URL

class App {
    private fun resource(filename: String) : URL {
        return this.javaClass.getResource(filename)!!
    }

    fun solve() {
        println(Day01(resource("/day01-input.txt")).solvePart1())
        println(Day01(resource("/day01-input.txt")).solvePart2())
    }
}

fun main() {
    App().solve()
}