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

        println("\nDay04")
        println(Day04(resource("/day04-input.txt")).solvePart1())
        println(Day04(resource("/day04-input.txt")).solvePart2())

        println("\nDay05")
        println(Day05(resource("/day05-input.txt")).solvePart1())
        println(Day05(resource("/day05-input.txt")).solvePart2())

        println("\nDay06")
        println(Day06(resource("/day06-input.txt")).solvePart1())
        println(Day06(resource("/day06-input.txt")).solvePart2())

        println("\nDay07")
        println(Day07(resource("/day07-input.txt")).solvePart1())
        println(Day07(resource("/day07-input.txt")).solvePart2())

        println("\nDay08")
        println(Day08(resource("/day08-input.txt")).solvePart1())
        println(Day08(resource("/day08-input.txt")).solvePart2())

        println("\nDay09")
        println(Day09(resource("/day09-input.txt")).solvePart1())
        println(Day09(resource("/day09-input.txt")).solvePart2())

        println("\nDay10")
        println(Day10(resource("/day10-input.txt")).solvePart1())
        Day10(resource("/day10-input.txt")).solvePart2() // RFZEKBFA

        println("\nDay11")
        println(Day11(resource("/day11-input.txt")).solvePart1())
        println(Day11(resource("/day11-input.txt")).solvePart2())

        println("\nDay12")
        println(Day12(resource("/day12-input.txt")).solvePart1())
        println(Day12(resource("/day12-input.txt")).solvePart2())

        println("\nDay13")
        println(Day13(resource("/day13-input.txt")).solvePart1())
        println(Day13(resource("/day13-input.txt")).solvePart2())

        println("\nDay14")
        println(Day14(resource("/day14-input.txt")).solvePart1())
    }
}

fun main() {
    App().solve()
}