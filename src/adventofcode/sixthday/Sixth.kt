package adventofcode.sixthday

import java.io.File

class Sixth {
    object First {
        @JvmStatic
        fun main(args: Array<String>) = doTheThing(input = "input/Day6TestInput.txt", finderFunction ={last()})
    }
    object Second {
        @JvmStatic
        fun main(args: Array<String>) = doTheThing(input = "input/Day6Input.txt", finderFunction = {first()})
    }
}

fun doTheThing(input: String, finderFunction: List<List<Char>>.() -> List<Char>) {
    val lines = File(input).readLines()
    val password: MutableList<Char> = mutableListOf()
    lines.map {
        it
            .trim()
            .toCharArray()
            .toList()}
            .transpose()
            .map { chars -> chars
                .groupBy { char -> char }
                .values
                .sortedBy { chars -> chars.size }
                .finderFunction()
                .first { secret -> password.add(secret) }
            }
    println(password.joinToString(""))
}

fun <T> List<List<T>>.transpose(): List<List<T>> {
    val columns: MutableList<MutableList<T>> = mutableListOf()
    repeat(this.first().size) { columns.add(mutableListOf()) }
    this.forEach { row -> row.forEachIndexed { index, value -> columns[index].add(value) } }
    return columns
}
