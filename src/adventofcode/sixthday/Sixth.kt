package adventofcode.sixthday

import java.io.File

class Sixth {
    object First {
        @JvmStatic
        fun main(args: Array<String>) = doTheThing(input = "input/Day6TestInput.txt", find ={last()})
    }
    object Second {
        @JvmStatic
        fun main(args: Array<String>) = doTheThing(input = "input/Day6Input.txt", find = {first()})
    }
}

fun doTheThing(input: String, find: List<List<Char>>.() -> List<Char>) {
    val lines = File(input).readLines()
    val password: MutableList<Char> = mutableListOf()
    lines
        .map(String::toCharArray)
        .transpose()
        .map { chars -> chars
            .groupBy { char -> char }
            .values
            .sortedBy(List<Char>::size)
            .find()
            .first { secret -> password.add(secret) }
        }
    println(password.joinToString(""))
}

fun List<CharArray>.transpose(): List<List<Char>> {
    val columns: MutableList<MutableList<Char>> = mutableListOf()
    repeat(this.first().size) { columns.add(mutableListOf()) }
    this.forEach { row -> row.forEachIndexed { index, value -> columns[index].add(value) } }
    return columns
}
