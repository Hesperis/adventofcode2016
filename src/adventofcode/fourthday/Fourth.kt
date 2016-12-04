package adventofcode.fourthday

import java.io.File

class Fourth {
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
    val lines = File("input/Day4Input.txt").readLines()
    val count = lines
            .filter(::isValidRooms)
            .map(::findSectorIds)
            .sum()
    println(count)
}

fun second() {
    val lines = File("input/Day4Input.txt").readLines()
    lines
            .filter(::isValidRooms)
            .map(::splitToWordsAndIndexModulator)
            .map(::decrypt)
            .filter { nameWithId -> "north" in nameWithId.first }
            .map { nameWithId -> println("Room name is ${nameWithId.first}, sector id is ${nameWithId.second}") }
}

fun splitString(string: String): List<String> {
    return string
            .trim()
            .split("[","]")
            .filter(String::isNotEmpty)
}

fun isValidRooms(string: String): Boolean {
    val room = splitString(string)
    val hash = room[1].toCharArray().toList()
    val sortedchars = room[0]
            .toCharArray()
            .filterNot { char -> char == '-' || char.isDigit() }
            .groupBy { char -> char }
            .toSortedMap()
            .toList()
            .sortedByDescending{charGroup -> charGroup.second.size}
            .subList(0,5)
            .map { charGroup -> charGroup.first }
    return sortedchars == hash
}

fun findSectorIds(string: String): Int {
    return string
            .toCharArray()
            .filter { it.isDigit() }
            .joinToString(separator = "").toInt()
}

fun splitToWordsAndIndexModulator(string: String): Pair<List<String>, Int> {
    val words = string.split('-').filterNot { it.first().isDigit() }
    val modulator = findSectorIds(string)
    return Pair(words,modulator)
}

fun decrypt(room: Pair<List<String>, Int>): Pair<String, Int> {
    val string = room.first
            .map{
                roomName -> roomName
                .toCharArray()
                .map {
                    getCharAtIndex(getIndexForChar(it) + room.second)
                }
                .joinToString(separator = "")
            }.joinToString(separator = " ")
    return Pair(string, room.second)
}

val alphabet = listOf('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')

fun getCharAtIndex(index: Int): Char {
    val reducedIndex = index%26
    return alphabet[reducedIndex]
}

fun getIndexForChar(char: Char): Int {
    if (char in alphabet) return alphabet.indexOf(char)
    else return 0
}