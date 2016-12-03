import java.io.File

private var currentPosition = '5'
private var currentIndex = 4
private var lowermost = listOf<Char>('5','A','D','C','9')
private var leftmost = listOf<Char>('1','2','5','A','D')
private var rightmost = listOf<Char>('1','4','9','C','D')
private var topmost = listOf<Char>('5','2','1','4','9')
private val keyarray = arrayListOf('1','2','3','4','5','6','7','8','9','A','B','C','D')
private var finalCode = mutableListOf<Char>()

fun main(args: Array<String>) {
    val lines = File("src/Day2input.txt").readLines()
    lines.forEach(::resolveCode)
    println("The final code is ${finalCode}")
}

fun resolveCode(input: String) {
    val instructions: MutableList<Direction> = mutableListOf()
    for (char in input) {
        instructions.add(getDirection(char))
    }
    getNextPosition(instructions)
    finalCode.add(currentPosition)
    println(currentPosition)
}

fun getNextPosition(instructions: MutableList<Direction>) {
    instructions.forEach {
        when(it) {
            Direction.DOWN -> {
                if(lowermost.contains(keyarray[currentIndex])) {
                    println("Staying at position ${currentPosition}")
                    return@forEach
                }
                else if (keyarray[currentIndex] == 'B' || keyarray[currentIndex] == '1'){
                    currentIndex += 2
                    currentPosition = keyarray[currentIndex]
                }
                else {
                    currentIndex += 4
                    currentPosition = keyarray[currentIndex]
                }
                println("Moving down to position ${currentPosition}")
            }
            Direction.RIGHT -> {
                if(rightmost.contains(keyarray[currentIndex])) {
                    println("Staying at position ${currentPosition}")
                    return@forEach
                }
                else {
                    currentIndex += 1
                    currentPosition = keyarray[currentIndex]
                }
                println("Moving right to position ${currentPosition}")
            }
            Direction.UP -> {
                if(topmost.contains(currentPosition)) {
                    println("Staying at position ${currentPosition}")
                    return@forEach
                }
                else if (keyarray[currentIndex] == '3' || keyarray[currentIndex] == 'D') {
                    currentIndex -= 2
                    currentPosition = keyarray[currentIndex]
                }
                else {
                    currentIndex -=4
                    currentPosition = keyarray[currentIndex]
                }
                println("Moving up to position ${currentPosition}")
            }
            Direction.LEFT -> {
                if(leftmost.contains(currentPosition)) {
                    println("Staying at position ${currentPosition}")
                    return@forEach
                }
                else {
                    currentIndex -= 1
                    currentPosition = keyarray[currentIndex]
                }
                println("Moving left to position ${currentPosition}")
            }
        }
    }
}

fun getDirection(char: Char): Direction {
    when(char) {
        'U' -> return Direction.UP
        'D' -> return Direction.DOWN
        'L' -> return Direction.LEFT
        'R' -> return Direction.RIGHT
    }
    return Direction.UP
}

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

