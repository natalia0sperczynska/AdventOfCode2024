package day1

import java.io.File

fun main() {
    val inputFilePathDay1 = "src/day1/test_input_day1"
    var leftNum = mutableListOf<Int>()
    var rightNum = mutableListOf<Int>()
    var result=0

    File(inputFilePathDay1).useLines { lines ->
        for (line in lines) {
            val numbers = line.trim().split("\\s+".toRegex()).map { it.toInt() }
            if (numbers.size == 2) {
                leftNum.add(numbers[0])
                rightNum.add(numbers[1])
            }
        }
    }

//
//    rightNum.sort()
//    leftNum.sort()
//
//     for (i in 0..<leftNum.size) {
//         result += abs((leftNum[i] - rightNum[i]))
//
//     }
//    println(result)

     //part2
    val map = mutableMapOf<Int, Int>()
    for (number1 in leftNum) {
        val count = rightNum.count { it == number1 }
        map[number1] = (map[number1] ?: 0) + count
    }

    var similarity = 0

    for ((key, value) in map) {
        similarity += (key * value)
    }
    println(similarity)

}