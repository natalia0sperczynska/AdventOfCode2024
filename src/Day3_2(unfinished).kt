
val inputFilePath2 = "src/input"

fun main() {
    val regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()
    val input = prepare(inputFilePath2)

    //val matchResult = regex.findAll(input)

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
        when { (regexDo.find(input, index) != null) -> {
            multiply = true
            index = regexDo.find(input, index)!!.range.last + 1
        }
            regexDont.find(input, index) != null -> {
                multiply = false
                index = regexDont.find(input, index)!!.range.last + 1
            }
            multiply -> {
                val matchResult = regex.findAll(input, index)

                if (matchResult.toList().isNotEmpty()) {
                    finalResult += mulAndAdd(matchResult)
                    index = matchResult.last().range.last + 1
                } else {
                    index++
                }
            }
            else -> {
                index++
            }
        }
    }

    println(finalResult)
}
//^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$
//expect fun splitToSequence(input: CharSequence, limit: Int = 0): Sequence<String>
//expect fun findAll(input: CharSequence, startIndex: Int = 0): Sequence<MatchResult>