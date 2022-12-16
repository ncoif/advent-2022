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
    fun solvePart1() {
        val testInput = this.javaClass.getResource("/day11-test-input.txt")!!
        assertEquals(10605, Day11(testInput).solvePart1())
    }

}