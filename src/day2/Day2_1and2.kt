package day2

import java.io.File
import kotlin.math.abs

fun safeCheck(sequence: String):Boolean {
    var increasing=true
    var decreasing=true
    var between=true
    val numbers = sequence.split("\\s+".toRegex())
        .map { it.trim().replace(",", "") }
        .map { it.toIntOrNull() }

    if (numbers.contains(null)) return false

    val validNumbers = numbers.filterNotNull()
    for (i in 0 until validNumbers.size-1){
        if (abs(validNumbers[i]-validNumbers[i+1]) !in 1..3){
            between=false
        }
        if (validNumbers[i]<validNumbers[i+1]){
            increasing=false
        }
        if (validNumbers[i]>validNumbers[i+1]){
            decreasing=false
        }
    }
    return (increasing || decreasing )&& between
}
fun modifySafeCheck(sequence: String):Boolean{
    if (safeCheck(sequence))return true

    val numbers = sequence.split("\\s+".toRegex()).map { it.toInt() }

    for( i in 0..numbers.size-1){
        val numberList = numbers.toMutableList()
        numberList.removeAt(i)
        if(safeCheck(numberList.joinToString())){
            return true
        }
    }
    return false

}
fun main() {
    val inputFilePathDay2 = "src/day2/test_input_day2"
    var safe = 0
    File(inputFilePathDay2).useLines { lines ->
        for (line in lines) {
            val sequence = line.trim()
            if (modifySafeCheck(sequence))
                safe +=1
        }
    }
    println(safe)
}

