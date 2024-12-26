import java.io.File

//val inputFilePath = "src/input"
fun prepare(filePath:String):String{
    var input=StringBuilder()
    File(filePath).useLines { lines ->
        for (line in lines) {
            input.append(line.trim())
        }
    }
    return input.toString()
}
fun mulAndAdd(matches:Sequence<MatchResult>):Int{
    var sum=0
    matches.forEach{mul->
        val(x,y)=mul.destructured
        val mul=x.toInt()*y.toInt()
        sum+=mul
    }
    return sum
}

fun main() {
    val regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()

    val matchResult=regex.findAll(prepare(inputFilePath2))

    val results = matchResult.map { it.value }.toList()
    println(results)
    println(mulAndAdd(matchResult))

}
//^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$
//expect fun splitToSequence(input: CharSequence, limit: Int = 0): Sequence<String>
//expect fun findAll(input: CharSequence, startIndex: Int = 0): Sequence<MatchResult>
