package day7

import day5.prepareToString
import kotlin.math.pow
val inputFilePathDay7="src/day7/test_input_day7"

class Equation(val answer: Int, val parts: List<Int>)

class Operation(val output: Int, val mask: Int)

fun getTheSum(input: String): Int {
    return equations(input).mapNotNull { canBeMathed(it) }.sum()
}

fun equations(input: String): List<Equation> {
    return input.lines().mapNotNull { toEquation(it) }
}

fun toEquation(line: String): Equation? {
    val parts = line.split(":").map { it.trim() }
    if (parts.size == 2) {
        val answer = parts[0].toIntOrNull()
        val equationParts = parts[1].split(" ")
            .mapNotNull { it.toIntOrNull() }
        if (answer != null && equationParts.isNotEmpty()) {
            return Equation(answer, equationParts)
        }
    }
    return null
}

fun canBeMathed(equation: Equation): Int? {
    val upperBound = getUpperBound(equation)
    return if (canBeMathedHelp(equation, upperBound, 0)) {
        equation.answer
    } else {
        null
    }
}

fun getUpperBound(equation: Equation): Int {
    return (2.0.pow((equation.parts.size - 1).toDouble()) - 1).toInt()
}

fun canBeMathedHelp(equation: Equation, upperBound: Int, value: Int): Boolean {
    if (value > upperBound) return false

    var operation = Operation(equation.parts[0], value)

    for (i in 1 until equation.parts.size) {
        operation = applyOperation(equation.parts[i], operation)
    }

    if (operation.output == equation.answer) {
        return true
    }

    return canBeMathedHelp(equation, upperBound, value + 1)
}

fun applyOperation(nextPart: Int, operation: Operation): Operation {
    val isAdd = operation.mask % 2 == 0
    val output = if (isAdd) {
        operation.output + nextPart
    } else {
        operation.output * nextPart
    }
    return Operation(output, operation.mask shr 1)
}

fun main() {
    val input7 = "190: 10 19\n" +
            "3267: 81 40 27\n" +
            "83: 17 5\n" +
            "156: 15 6\n" +
            "7290: 6 8 6 15\n" +
            "161011: 16 10 13\n" +
            "192: 17 8 14\n" +
            "21037: 9 7 18 13\n" +
            "292: 11 6 16 20"

    val input= prepareToString(inputFilePathDay7)
    val result = getTheSum(input)
    println(result)
}
