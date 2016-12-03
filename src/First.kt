private var xCoordinate = 0
private var yCoordinate = 0
private var direction = 0
private var visitedLocations: MutableList<Pair<Int,Int>> = mutableListOf(Pair(xCoordinate,yCoordinate))
private var secretCoordinates = Pair(0,0)
private var firstPosFound = false
private val startInput1 = "L3, R1, L4, L1, L2, R4, L3, L3, R2, R3, L5, R1, R3, L4, L1, L2, R2, R1, L4, L4, R2, L5, R3, R2, R1, L1, L2, R2, R2, L1, L1, R2, R1, L3, L5, R4, L3, R3, R3, L5, L190, L4, R4, R51, L4, R5, R5, R2, L1, L3, R1, R4, L3, R1, R3, L5, L4, R2, R5, R2, L1, L5, L1, L1, R78, L3, R2, L3, R5, L2, R2, R4, L1, L4, R1, R185, R3, L4, L1, L1, L3, R4, L4, L1, R5, L5, L1, R5, L1, R2, L5, L2, R4, R3, L2, R3, R1, L3, L5, L4, R3, L2, L4, L5, L4, R1, L1, R5, L2, R4, R2, R3, L1, L1, L4, L3, R4, L3, L5, R2, L5, L1, L1, R2, R3, L5, L3, L2, L1, L4, R4, R4, L2, R3, R1, L2, R1, L2, L2, R3, R3, L1, R4, L5, L3, R4, R4, R1, L2, L5, L3, R1, R4, L2, R5, R4, R2, L5, L3, R4, R1, L1, R5, L3, R1, R5, L2, R1, L5, L2, R2, L2, L3, R3, R3, R1"

fun main(args: Array<String>) {
    val usableInput = startInput1.split(',')
    for (arg in usableInput) {
        chooseTurn(arg.trim())
    }
    val distance = calcDistance(secretCoordinates.first, secretCoordinates.second)
    println("Distance to the HQ is ${distance}")
}


fun chooseTurn(arg: String) {
    var turn = 0
    if (arg[0] == 'L') turn = 1
    val stepsToGo = arg.substring(1).toInt()
    when (direction) {
        0 -> {
            if(turn == 0) changeCoords(stepsToGo, 0)
            else changeCoords(-stepsToGo, 0)
        }
        1 -> {
            if(turn == 0) changeCoords(0, -stepsToGo)
            else changeCoords(0, stepsToGo)
        }
        2 -> {
            if(turn == 0) changeCoords(-stepsToGo, 0)
            else changeCoords(stepsToGo, 0)
        }
        3 -> {
            if(turn == 0) changeCoords(0, stepsToGo)
            else changeCoords(0, -stepsToGo)
        }
    }
    if(turn == 0) changeDirectionRight()
    else changeDirectionLeft()
}

fun changeCoords(a: Int, b: Int) {
        var k = 0
        var j = 0
        if(a > 0) {
            while (k < a) {
                xCoordinate = xCoordinate.plus(1)
                checkAndStoreCoordinates()
                k++
            }
        }
        else if(a < 0) {
            while (k > a) {
                xCoordinate = xCoordinate.minus(1)
                checkAndStoreCoordinates()
                k--
            }
        }
        if(b > 0) {
            while (j < b) {
                yCoordinate = yCoordinate.plus(1)
                checkAndStoreCoordinates()
                j++
            }
        }
        else if (b < 0) {
            while (j > b) {
                yCoordinate = yCoordinate.minus(1)
                checkAndStoreCoordinates()
                j--
            }
        }
    }

fun changeDirectionRight() {
    if(direction == 3) direction = 0
    else direction += 1
}

fun changeDirectionLeft() {
    if(direction == 0) direction = 3
    else direction -= 1
}

fun checkAndStoreCoordinates() {
    val currentPosition = Pair(xCoordinate, yCoordinate)
    if (visitedLocations.contains(currentPosition) && !firstPosFound) {
        println("${currentPosition} is the first position to be visited twice")
        secretCoordinates = currentPosition
        firstPosFound = true
    }
    visitedLocations.add(currentPosition)
}

fun calcDistance(a: Int, b: Int): Int {
    return Math.abs(a) + Math.abs(b)
}


