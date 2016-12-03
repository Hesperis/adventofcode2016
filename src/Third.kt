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

private var allTriads :MutableList<List<Int>> = mutableListOf()

fun first() {
    val lines = File("input/Day3Input.txt").readLines()
    var count = 0
    lines.forEach { if(tryIfValidTriangle(it.trim().replace("\\s+".toRegex(),","))) count++ }
    println("Number of valid triangles is $count")
}

fun tryIfValidTriangle(triangleString: String): Boolean {
    val triangle = triangleString.split(",").map(String::toInt).sorted()
    return triangle.all { triangle[2] < triangle[1] + triangle[0] }
}

fun second() {
    val lines = File("input/Day3input.txt").readLines()
    val trimmedLines = lines.map { it.trim().replace("\\s+".toRegex(),",")}
    val columns: List<MutableList<Int>> = listOf(mutableListOf(), mutableListOf(), mutableListOf())

    trimmedLines.map { it.split(',') }
            .forEach{
                columns.get(0).add(it.get(0).toInt())
                columns.get(1).add(it.get(1).toInt())
                columns.get(2).add(it.get(2).toInt())
    }
    columns.forEach(::splitByThree)
    var count = 0
    allTriads.forEach {
        if(it[0] + it[1] > it[2]) count++
    }
    println(count)
}

fun splitByThree(column: List<Int>){
    var i = 0
    while (i < column.size) {
        val snippet = listOf<Int>(column.get(i), column.get(i+1), column.get(i+2))
        allTriads.add(snippet.sorted())
        i += 3
    }

}
