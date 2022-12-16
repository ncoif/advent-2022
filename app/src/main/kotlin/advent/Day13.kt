package advent

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.IntNode
import java.io.File
import java.net.URL

class Day13(private val input: URL) {
    private val objectMapper = ObjectMapper()

    // return true if in the right order
    fun comparePair(left: String, right: String) : Boolean {
        val leftNode = objectMapper.readTree(left)
        val rightNode = objectMapper.readTree(right)

        // if bo
        return compareArray(leftNode, rightNode)!!
    }

    // return true if in the right order, false if not, null if undecided, and we need to keep comparing
    private fun compareArray(left: JsonNode, right: JsonNode) : Boolean? {
        require(left.isArray) { "Only support array and int" }
        require(right.isArray) { "Only support array and int" }

        val leftIterator = left.iterator()
        val rightIterator = right.iterator()
        while(leftIterator.hasNext() || rightIterator.hasNext()) {

            // Left side ran out of items, so inputs are in the right order
            if (!leftIterator.hasNext()) {
                return true
            }

            // Right side ran out of items, so inputs are not in the right order
            if (!rightIterator.hasNext()) {
                return false
            }

            val first = leftIterator.next()
            val second = rightIterator.next()

            if (first.isInt && second.isInt) {
                // If both values are integers
                if (first.asInt() == second.asInt()) {
                    continue
                } else {
                    return first.asInt() < second.asInt()
                }
            } else if (first.isArray && second.isArray) {
                // If both values are lists
                val arrayResult = compareArray(first, second)
                if (arrayResult != null) {
                    return arrayResult
                } else {
                    continue
                }
            } else if (first.isArray && !second.isArray){
                // If exactly one value is an integer, mixed type
                return compareArray(first, makeArray(second))
            } else if (!first.isArray && second.isArray) {
                // If exactly one value is an integer, mixed type
                return compareArray(makeArray(first), second)
            } else {
                throw IllegalArgumentException("Only support array and int")
            }
        }
        return null
    }

    private fun makeArray(intNode: JsonNode) : JsonNode {
        require(intNode is IntNode)
        val arrayNode = objectMapper.createArrayNode()
        arrayNode.add(intNode.asInt())
        return arrayNode
    }

    fun solvePart1(): Int {
        val lines = File(input.toURI()).readLines()

        return 0
    }


}