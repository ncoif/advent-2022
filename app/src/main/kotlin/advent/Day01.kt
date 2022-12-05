package advent

import java.io.File

class Day01(inputFileName: String) {
    fun readInput(fileName: String) {
        File(fileName).forEachLine { println(it) }
    }

//    fun solve(): Int {
//        readInput(this.inputFileName)
//        return 0
//    }
}