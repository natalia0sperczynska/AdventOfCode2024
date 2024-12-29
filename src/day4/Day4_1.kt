package day4

import java.io.File
val inputFilePathDay4 = "src/day4/test_input_day4"

val input="....XXMAS.\n" +
        ".SAMXMS...\n" +
        "...S..A...\n" +
        "..A.A.MS.X\n" +
        "XMASAMX.MM\n" +
        "X.....XA.A\n" +
        "S.S.S.S.SS\n" +
        ".A.A.A.A.A\n" +
        "..M.M.M.MM\n" +
        ".X.X.XMASX"

fun prepareToList(filePath:String):List<String>{
    var inputList= mutableListOf<String>()
    File(filePath).useLines { lines ->
        for (line in lines) {
            inputList.add(line.trim())
        }
    }
    return inputList
}
// Function to transpose from GitHub
fun <T> List<List<T>>.transpose(): List<List<T>> {
    var transposed = mutableListOf<List<T>>()
    for (i in first().indices) {
        val col: MutableList<T> = ArrayList()
        forEach { row ->
            col.add(row[i])
        }
        transposed.add(col)
    }
    return transposed
}

fun xmasCountHorizontal(grid: List<String>, regex: Regex):Int {
    var count = 0
    for (line in grid) {
        val matchResultXmas=regex.findAll(line)
        val results = matchResultXmas.map { it.value }.toList()
        count+=results.size
    }
    return count
}
fun xmasCountVertical(grid: List<String>, regex: Regex): Int{
    val cols=grid[0].length
    var count =0

    for(col in 0 until cols ){
        val columnString = grid.map { it[col] }.joinToString("")
        val matchResultXmas=regex.findAll(columnString)
        val results = matchResultXmas.map { it.value }.toList()
        count+=results.size
    }
    return count
}
fun xmasCountDiagonal(grid: List<String>, regex: Regex): Int{
    var count =0
    val diagonals = getAllDiagonals(grid)
    for (diag in diagonals) {
        val matchResultXmas=regex.findAll(diag)
        val results = matchResultXmas.map { it.value }.toList()
        count+=results.size
    }
    return count
}
fun getAllDiagonals(grid: List<String>):List<String>{
    // (0,0), (0,1), (2,3), ...(9,9)
    //(1,0), (2,1), (3,2),...(9,8)
    //...
    //(9,0)
    //(0,1), (1,2)...(8,9)
    //...
    //(0,9)
    val rows = grid.size
    val cols = grid[0].length
    val diagonals = mutableListOf<String>()

    for (d in -(rows - 1) until cols) {
        val diagonal = StringBuilder()
        for (i in 0 until rows) {
            val j = i + d
            if (j in 0 until cols) diagonal.append(grid[i][j])
        }
        if (diagonal.isNotEmpty()) diagonals.add(diagonal.toString())
    }

    // Top-right to bottom-left diagonals
    for (d in 0 until rows + cols - 1) {
        val diagonal = StringBuilder()
        for (i in 0 until rows) {
            val j = d - i
            if (j in 0 until cols) diagonal.append(grid[i][j])
        }
        if (diagonal.isNotEmpty()) diagonals.add(diagonal.toString())
    }

    return diagonals
}
fun totalSum(sumV:Int,sumH:Int,sumD:Int):Int{
    return sumV+sumD+sumH
}
fun main(){
    val regexXmas="XMAS".toRegex()
    val regexSamx="SAMX".toRegex()

    val inputList = prepareToList(inputFilePathDay4)

    println(inputList)

    var sumHorizotnal=0
    sumHorizotnal = xmasCountHorizontal(inputList,regexXmas) + xmasCountHorizontal(inputList,regexSamx)

    println(sumHorizotnal)

    var sumVerical=0
    sumVerical = xmasCountVertical(inputList,regexXmas) + xmasCountVertical(inputList,regexSamx)

    println(sumVerical)

    var sumDiagonal=0
    sumDiagonal = xmasCountDiagonal(inputList,regexXmas) + xmasCountDiagonal(inputList,regexSamx)

    println(sumDiagonal)
    println(totalSum(sumDiagonal,sumVerical,sumHorizotnal))

//    var listAfter=listOf(inputList, inputList).transpose()
//    println(listAfter)


}