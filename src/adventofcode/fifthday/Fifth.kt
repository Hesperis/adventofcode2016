package adventofcode.fifthday

import org.apache.commons.codec.binary.Hex
import java.security.MessageDigest

class Fifth {
    object First {
        @JvmStatic
        fun main(args: Array<String>) = first()
    }
    object Second {
        @JvmStatic
        fun main(args: Array<String>) = second()
    }
}

private val input = "uqwqemis"
private val md: MessageDigest = MessageDigest.getInstance("MD5")

fun first() {
    val password = getSimplePassword(input)
    println (password)
}

fun  getSimplePassword(input: String): String {
    var index = 0
    val password: MutableList<Char> = mutableListOf()
    while (password.size < 8 && index < Int.MAX_VALUE) {
        val hexString = getHex(index, input)!!
        if (hexString.startsWith("00000")) {
            password.add(hexString[5])
        }
        index += 1
    }
    return password.joinToString("")
}

fun second() {
    val password = getCoolComplicatedPassword(input)
    println(password)
}

fun getCoolComplicatedPassword(input: String): String {
    var index = 0
    val password = CharArray(8)
    while (!isComplete(password) && index < Int.MAX_VALUE) {
        val hexString = getHex(index, input)!!
        if (hexString.startsWith("00000") && isValidPosition(hexString[5],password)) {
            password[hexString[5].toString().toInt()] = hexString[6]
            println(password.joinToString(""))
        }
        index += 1
    }
    return password.joinToString("")
}

private fun getHex(index: Int, input: String): String? {
    val bytes = input.plus(index.toString()).toByteArray()
    md.update(bytes, 0, bytes.size)
    val hexString = Hex.encodeHexString(md.digest())
    return hexString
}

private fun isComplete(password:CharArray): Boolean {
    return password.all { it.toInt() != 0 }
}

private fun isValidPosition(char: Char, password: CharArray): Boolean {
    if (!char.isDigit()) return false
    else if (char.toString().toInt() < 8) {
        return password[char.toString().toInt()].toInt() == 0
    }
    else return false
}