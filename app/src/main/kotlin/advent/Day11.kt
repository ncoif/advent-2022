package advent

import java.io.File
import java.net.URL

class Day11(private val input: URL) {

    data class MonkeyId(private val id: Int)
    data class Item(var worry: Int)

    enum class OperationType { ADD, MULTIPLY, SQUARE}
    data class Operation(private val type: OperationType, private val amount: Int?)

    data class Condition(private val divisibleBy: Int, val monkeyIdTrue: MonkeyId, val monkeyIdFalse: MonkeyId)

    data class Monkey(val monkeyId: MonkeyId, val operation: Operation, val condition: Condition, val items: MutableList<Item>)

    private fun parseMonkeyId(line: String): MonkeyId {
        //Monkey 0:
        val lineItem = line.split(" ")
        val id = lineItem[1].substringBefore(":")
        return MonkeyId(id.toInt())
    }

    private fun parseItems(line: String): MutableList<Item> {
        //Starting items: 79, 98
        val items = line.split(": ")[1]
        val lineItems = items.split(", ")
        return lineItems.map { it.toInt() }.map { Item(it) }.toMutableList()
    }

    private fun parseOperation(line: String): Operation {
        //Operation: new = old * 19
        val operationString = line.substringAfter("Operation: new = old ")
        val operationParts = operationString.split(" ")

        return if (operationParts[0] == "+") {
            Operation(OperationType.ADD, operationParts[1].toInt())
        } else if (operationParts[0] == "*" && operationParts[1] == "old") {
            Operation(OperationType.SQUARE, null)
        } else if (operationParts[0] == "*") {
            Operation(OperationType.MULTIPLY, operationParts[1].toInt())
        } else {
            throw IllegalArgumentException()
        }
    }

    private fun parseConditionAmount(line: String): Int {
        //Test: divisible by 23
        val conditionParts = line.substringAfter("Test: divisible by ")
        return conditionParts.toInt()
    }

    private fun parseMonkeyIdCondition(line: String): MonkeyId {
        //If true: throw to monkey 2
        //If false: throw to monkey 3
        val monkeyIdPart = line.substringAfter("throw to monkey ")
        return MonkeyId(monkeyIdPart.toInt())
    }

    fun parse(iterator: Iterator<String>) : List<Monkey> {
        val monkeys = mutableListOf<Monkey>()
        while(iterator.hasNext()) {
            val monkeyId = parseMonkeyId(iterator.next())
            val items = parseItems(iterator.next())
            val operation = parseOperation(iterator.next())

            val conditionAmount = parseConditionAmount(iterator.next())
            val monkeyIdTrue = parseMonkeyIdCondition(iterator.next())
            val monkeyIdFalse = parseMonkeyIdCondition(iterator.next())
            val condition = Condition(conditionAmount, monkeyIdTrue, monkeyIdFalse)

            monkeys.add(Monkey(monkeyId, operation, condition, items))

            // skip blank line
            if (iterator.hasNext()) {
                iterator.next()
            }
        }
        return monkeys
    }

    fun solvePart1(): Int {
        val input = File(input.toURI()).readLines()
        return 0
    }
}