package advent

import java.net.URL

class App {
    private fun resource(filename: String) : URL {
        return this.javaClass.getResource(filename)!!
    }

    fun solve() {
        println("Day01")
        println(Day01(resource("/day01-input.txt")).solvePart1())
        println(Day01(resource("/day01-input.txt")).solvePart2())

        println("\nDay02")
        println(Day02(resource("/day02-input.txt")).solvePart1())
        println(Day02(resource("/day02-input.txt")).solvePart2())

        println("\nDay03")
        println(Day03(resource("/day03-input.txt")).solvePart1())
        println(Day03(resource("/day03-input.txt")).solvePart2())
    }
}

fun main() {
    App().solve()
}