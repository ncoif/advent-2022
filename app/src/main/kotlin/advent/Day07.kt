package advent

import java.io.File
import java.net.URL

class Day07(private val input: URL) {

    interface Node {
        fun size(): Long

        fun print(buffer: StringBuilder, prefix: String, childrenPrefix: String)

        fun prettyString(): String {
            val sb = StringBuilder()
            print(sb, "- ", "  ")
            return sb.toString()
        }
    }
    data class File(val name: String, val size: Long): Node {
        override fun size(): Long {
            return size
        }

        override fun print(buffer: StringBuilder, prefix: String, childrenPrefix: String) {
            buffer.append(prefix)
            buffer.append("$name (file, size=$size)")
            buffer.append("\n")
        }
    }

    data class Dir(val name:String, val files: MutableList<Node>): Node {
        override fun size(): Long {
            return files.sumOf { it.size() }
        }

        override fun print(buffer: StringBuilder, prefix: String, childrenPrefix: String) {
            buffer.append(prefix)
            buffer.append("$name (dir size=${size()})")
            buffer.append("\n")
            for (file in files) {
                file.print(buffer, "$childrenPrefix- ", "$childrenPrefix  ")
            }
        }
    }

    fun parseFileSystem(lines: List<String>): Node {
        val root = Dir("/", mutableListOf())
        parseNode(lines.drop(1).iterator(), root)
        return root
    }

    private fun parseNode(iterator: Iterator<String>, currentNode : Dir) {
        while (iterator.hasNext()) {
            val line = iterator.next()

            if (line == "$ ls") {
                continue
            } else if (line.equals("$ cd ..")) {
                return
            } else if (line.startsWith("$ cd ")) {
                val dirName = line.split(" ").last()
                val nextNode = currentNode.files
                    .filterIsInstance<Dir>()
                    .first { it.name == dirName }
                parseNode(iterator, nextNode)
                continue
            } else {
                val lineItem = line.split(" ")
                if (lineItem[0] == "dir") {
                    currentNode.files.add(Dir(lineItem[1], mutableListOf()))
                } else {
                    currentNode.files.add(File(lineItem[1], lineItem[0].toLong()))
                }
            }
        }
    }

    private fun allNodes(root: Node) : List<Node> {
        val allNodesResult = mutableListOf<Node>()
        allNodes(root, allNodesResult)
        return allNodesResult
    }

    private fun allNodes(node: Node, allNodesResult: MutableList<Node>) {
        allNodesResult.add(node)
        if (node is Dir) {
            node.files.forEach { allNodes(it, allNodesResult)}
        }
    }

    fun solvePart1(): Long {
        val fileSystem = parseFileSystem(File(input.toURI()).readLines())

        return allNodes(fileSystem)
            .filterIsInstance<Dir>()
            .filter { it.size() < 100000 }
            .sumOf { it.size() }
    }

    fun solvePart2(): Long {
        val fileSystem = parseFileSystem(File(input.toURI()).readLines())

        val unusedSpace = 70000000 - fileSystem.size()
        val thresholdForDeletion = 30000000 - unusedSpace

        val deleteCandidate = allNodes(fileSystem)
            .filterIsInstance<Dir>()
            .filter { it.size() >= thresholdForDeletion }
            .sortedBy { it.size() }

        //println("would delete ${deleteCandidate.first()} with a size of ${deleteCandidate.first().size()}")

        return deleteCandidate.first().size()
    }


}