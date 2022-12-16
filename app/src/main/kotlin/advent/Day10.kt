package advent

import java.io.File
import java.net.URL

class Day10(private val input: URL) {

    enum class Type {
        NOOP, ADDX
    }
    data class Instruction(val type: Type, val value: Int?)

    private fun parse(line: String) : Instruction {
        val lineItem = line.split(" ")
        return when (lineItem[0]) {
            "noop" -> Instruction(Type.NOOP, null)
            "addx" -> Instruction(Type.ADDX, lineItem[1].toInt())
            else -> throw IllegalArgumentException()
        }
    }

    // extension function on the register \o/
    private fun List<Int>.cycleValue(cycleNumber: Int) : Int {
        return cycleNumber * this[cycleNumber - 1]
    }

    private fun List<Int>.isPixelLit(cycleNumber: Int, positionDraw: Int) : Boolean {
        val sprite0 = this[cycleNumber - 1]
        val sprite1 = sprite0 + 1
        val sprite2 = sprite0 + 2
        return positionDraw == sprite0 || positionDraw == sprite1 || positionDraw == sprite2
    }

    fun solvePart1(): Int {
        val input = File(input.toURI()).readLines()
        val instructions = input.map { parse(it) }

        val registerX = mutableListOf<Int>()
        registerX.add(1)

        for (instruction in instructions) {
            when (instruction.type) {
                Type.NOOP -> {
                    registerX.add(registerX.last())
                }
                Type.ADDX -> {
                    registerX.add(registerX.last())
                    registerX.add(registerX.last() + (instruction.value ?: 0))
                }
            }
        }

        return  registerX.cycleValue(20) +
                registerX.cycleValue(60) +
                registerX.cycleValue(100) +
                registerX.cycleValue(140) +
                registerX.cycleValue(180) +
                registerX.cycleValue(220)
    }

    fun solvePart2() {
        val input = File(input.toURI()).readLines()
        val instructions = input.map { parse(it) }

        val registerX = mutableListOf<Int>()
        registerX.add(1)

        for (instruction in instructions) {
            when (instruction.type) {
                Type.NOOP -> {
                    registerX.add(registerX.last())
                }
                Type.ADDX -> {
                    registerX.add(registerX.last())
                    registerX.add(registerX.last() + (instruction.value ?: 0))
                }
            }
        }

        for (line in 0..5) {
            for (char in 1..40) {
                val cycle = (line * 40) + char
                if (registerX.isPixelLit(cycle, char)) {
                    print("#")
                } else {
                    print(".")
                }
            }
            println()
        }
    }
}