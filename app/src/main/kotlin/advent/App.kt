package advent

import java.net.URL

class App {
    private fun resource(filename: String) : URL {
        return this.javaClass.getResource(filename)!!
    }

    fun solve() {
        println(Day01(resource("/day01-input.txt")).solve())
    }
}

fun main() {
    App().solve()
}