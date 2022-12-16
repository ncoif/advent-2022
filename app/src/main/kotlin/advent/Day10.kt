package advent

import java.io.File
import java.net.URL
import kotlin.math.abs

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
}