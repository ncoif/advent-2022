package advent

import java.io.File
import java.lang.IllegalStateException
import java.net.URL

class Day03(private val input: URL) {

    data class Item(val type: Char, val priority: Int)
    data class Bag(val items: List<Item>) {
        fun size(): Int {
            return items.size
        }
    }

    private fun extract(line: String) : Bag {
        val items = line.toCharArray().map { Item(it, priority(it)) }
        return Bag(items)
    }

    fun priority(type: Char) : Int {
        return if (type.code >= 97) {
            type.code - 97 + 1
        } else {
            type.code - 65 + 1 + 26
        }
    }

    private fun sharedItem(bag: Bag) : Item {
        val firstCompartment = Bag(bag.items.subList(0, (bag.size() / 2)))
        val secondCompartment = Bag(bag.items.subList((bag.size() / 2), bag.size()))

        val sharedItems = firstCompartment.items.intersect(secondCompartment.items.toSet())

        return sharedItems.first()
    }

    private fun sharedItem(bags: List<Bag>) : Item {
        var sharedItems = bags.first().items.toSet()
        for (bag in bags) {
            sharedItems = sharedItems.intersect(bag.items.toSet())
        }

        return sharedItems.first()
    }

    fun solvePart1(): Int {
        val bags = File(input.toURI()).readLines().map { extract(it) }

        return bags.stream()
            .map { sharedItem(it) }
            .mapToInt { it.priority }
            .sum()
    }

    fun solvePart2(): Int {
        val bags = File(input.toURI()).readLines().map { extract(it) }
        return bags.chunked(3).stream()
            .map { sharedItem(it) }
            .mapToInt{ it.priority}
            .sum()
    }

}