package adventofcode.thirdday

import java.io.File

class Third {
    object First {
        @JvmStatic
        fun main(args: Array<String>) = first()
    }
    object Second {
        @JvmStatic
        fun main(args: Array<String>) = second()
    }
}

fun first() {
    val lines = File("input/Day3Input.txt").readLines()
    val count = lines
            .map {
                it
                    .trim()
                    .split("\\s+".toRegex())
                    .map(String::toInt)
                    .sorted()
            }
            .filter { it[2] < it[0] + it[1] }.count()
    println("Number of valid triangles is $count")
}


fun second() {
    val lines = File("input/Day3input.txt").readLines()

    val columns: List<MutableList<Int>> = listOf(mutableListOf(), mutableListOf(), mutableListOf())

    lines.map { it.trim().split("\\s+".toRegex()) }
            .forEach{
                columns.get(0).add(it.get(0).toInt())
                columns.get(1).add(it.get(1).toInt())
                columns.get(2).add(it.get(2).toInt())
    }

    val allTriples = columns
        .flatten()
        .splitByThree()
        .map{ it.sorted() }

    val count = allTriples.filter {
        (it[0] + it[1] > it[2])
    }.count()

    println("Valid triangles are $count")
}

fun <T> List<T>.splitByThree(): List<List<T>>{
    return this
            .withIndex()
            .groupBy { v -> v.index / 3 }
            .values
            .map{ it.map {it -> it.value}}
}
