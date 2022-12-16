package advent

import java.io.File
import java.net.URL

class Day11(private val input: URL) {

    data class MonkeyId(private val id: Int)
    data class Item(var worry: Int) {
        fun apply(operation: Operation) {
            worry = operation.apply(worry)
        }

        fun applyBoredom() {
            worry /= 3
        }
    }

    enum class OperationType { ADD, MULTIPLY, SQUARE}
    data class Operation(private val type: OperationType, private val amount: Int?) {
        fun apply(value: Int) = when(type) {
            OperationType.ADD -> value + (amount ?: 0)
            OperationType.MULTIPLY -> value * (amount ?: 0)
            OperationType.SQUARE -> value * value
        }
    }

    data class Condition(private val divisibleBy: Int, val monkeyIdTrue: MonkeyId, val monkeyIdFalse: MonkeyId) {
        fun test(item: Item): MonkeyId {
            return if (item.worry % divisibleBy == 0) { monkeyIdTrue } else { monkeyIdFalse }
        }
    }

    data class Monkey(val monkeyId: MonkeyId, val operation: Operation, val condition: Condition, val items: MutableList<Item>) {
        private fun add(item: Item) = items.add(item)

        private fun processOneItem(monkeys: List<Monkey>) {
            val itemProcessed = items.removeAt(0)

            itemProcessed.apply(operation)
            itemProcessed.applyBoredom()

            val sendToMonkeyId = condition.test(itemProcessed)
            monkeys.first { it.monkeyId == sendToMonkeyId }.add(itemProcessed)
        }

        fun processItems(monkeys: List<Monkey>) {
            while(items.isNotEmpty()) {
                processOneItem(monkeys)
            }
        }
    }

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

    fun processOneRound(monkeys: List<Monkey>) {
        for (monkey in monkeys) {
            monkey.processItems(monkeys)
        }
        for (monkey in monkeys) {
            println("Monkey ${monkey.monkeyId}: ${monkey.items.map { it.worry }}")
        }
    }

    fun solvePart1(): Int {
        val lines = File(input.toURI()).readLines()
        val monkeys = parse(lines.iterator())

        processOneRound(monkeys)

        return 0
    }
}