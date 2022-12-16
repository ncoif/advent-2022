package advent

import advent.Day11.*
import advent.Day11.OperationType.*
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

internal class Day11Test {

    @Test
    fun testMonkeys() {
        val testInput = this.javaClass.getResource("/day11-test-input.txt")!!
        val monkeys = Day11(testInput).parse(File(testInput.toURI()).readLines().iterator())

        assertEquals(4, monkeys.size)

/*
Monkey 0:
  Starting items: 79, 98
  Operation: new = old * 19
  Test: divisible by 23
    If true: throw to monkey 2
    If false: throw to monkey 3
 */
        val firstMonkey = monkeys[0]
        assertEquals(Operation(MULTIPLY, 19), firstMonkey.operation)
        assertEquals(Condition(23, MonkeyId(2), MonkeyId(3)), firstMonkey.condition)
        assertEquals(mutableListOf(Item(79), Item(98)), firstMonkey.items)

/*
Monkey 1:
  Starting items: 54, 65, 75, 74
  Operation: new = old + 6
  Test: divisible by 19
    If true: throw to monkey 2
    If false: throw to monkey 0
 */
        val secondMonkey = monkeys[1]
        assertEquals(Operation(ADD, 6), secondMonkey.operation)
        assertEquals(Condition(19, MonkeyId(2), MonkeyId(0)), secondMonkey.condition)
        assertEquals(mutableListOf(Item(54), Item(65), Item(75), Item(74)), secondMonkey.items)
/*
Monkey 2:
  Starting items: 79, 60, 97
  Operation: new = old * old
  Test: divisible by 13
    If true: throw to monkey 1
    If false: throw to monkey 3
 */
        val thirdMonkey = monkeys[2]
        assertEquals(Operation(SQUARE, null), thirdMonkey.operation)
        assertEquals(Condition(13, MonkeyId(1), MonkeyId(3)), thirdMonkey.condition)
        assertEquals(mutableListOf(Item(79), Item(60), Item(97)), thirdMonkey.items)
    }

    @Test
    fun testProcessOneRound() {
        val testInput = this.javaClass.getResource("/day11-test-input.txt")!!
        val underTest = Day11(testInput)
        val monkeys = underTest.parse(File(testInput.toURI()).readLines().iterator())

        underTest.processOneRound(monkeys)

/*
Monkey 0: 20, 23, 27, 26
Monkey 1: 2080, 25, 167, 207, 401, 1046
Monkey 2:
Monkey 3:
 */
        assertEquals(listOf(20L, 23L, 27L, 26L), monkeys[0].items.map { it.worry })
        assertEquals(listOf(2080L, 25L, 167L, 207L, 401L, 1046L), monkeys[1].items.map { it.worry })
        assertEquals(listOf(), monkeys[2].items.map { it.worry })
        assertEquals(listOf(), monkeys[3].items.map { it.worry })

        underTest.processOneRound(monkeys)

/*
Monkey 0: 695, 10, 71, 135, 350
Monkey 1: 43, 49, 58, 55, 362
Monkey 2:
Monkey 3:
 */
        assertEquals(listOf(695L, 10L, 71L, 135L, 350L), monkeys[0].items.map { it.worry })
        assertEquals(listOf(43L, 49L, 58L, 55L, 362L), monkeys[1].items.map { it.worry })
        assertEquals(listOf(), monkeys[2].items.map { it.worry })
        assertEquals(listOf(), monkeys[3].items.map { it.worry })
    }

    @Test
    fun solvePart1() {
        val testInput = this.javaClass.getResource("/day11-test-input.txt")!!
        assertEquals(10605, Day11(testInput).solvePart1())
    }

    @Test
    fun solvePart2() {
        val testInput = this.javaClass.getResource("/day11-test-input.txt")!!
        assertEquals(2713310158L, Day11(testInput).solvePart2())
    }
}