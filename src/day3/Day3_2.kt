package day3

val inputFilePathDay3_2 = "src/day3/test_input_day3_part2"
val inpu="xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

fun main() {
    val regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()
    val input = prepare(inputFilePathDay3_2)

    //val matchResult = regex.findAll(test_input_day6)

    //val results = matchResult.map { it.value }.toList()
    //println(results)
    //println(mulAndAdd(matchResult))
    //--------------------------part2-----------------------------
    val regexDo = "do\\(\\)".toRegex()
    val regexDont = "don't\\(\\)".toRegex()
    var multiply = true
    var index = 0

    var finalResult=0


    while (index < input.length) {
        when {
            regexDo.find(input, index)?.range?.first == index -> {
                multiply = true
                index = regexDo.find(input, index)!!.range.last + 1
            }

            regexDont.find(input, index)?.range?.first == index -> {
                multiply = false
                index = regexDont.find(input, index)!!.range.last + 1
            }

            regex.find(input, index)?.range?.first == index -> {
                if (multiply) {
                    val match = regex.find(input, index)!!
                    val (x, y) = match.destructured
                    finalResult += x.toInt() * y.toInt()
                }
                index = regex.find(input, index)!!.range.last + 1
            }

            else -> index++
        }
    }

    println(finalResult)
}
//^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$
//expect fun splitToSequence(test_input_day6: CharSequence, limit: Int = 0): Sequence<String>
//expect fun findAll(test_input_day6: CharSequence, startIndex: Int = 0): Sequence<MatchResult>