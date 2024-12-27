import java.io.File

val inputFilePathDay5="src/input"

fun prepareToString(filePath:String):String{
    var input=StringBuilder()
    File(filePath).useLines { lines ->
        for (line in lines) {
            input.append(line).append("\n")
        }
    }
    return input.toString()
}
fun to2dIntArray(updates:List<String>):Array<IntArray>{
    return updates
        .filter { it.isNotBlank() }
        .map { line ->
            line.split(",")
                .filter { it.isNotBlank() }
                .map { it.toInt() }
                .toIntArray()
        }
        .toTypedArray()
}
fun isValidUpdate(arr:IntArray, allowed: MutableMap<Int, MutableSet<Int>>, notallowed: MutableMap<Int, MutableSet<Int>>):Boolean{
    for (i in 0 until arr.size) {
        for (j in i + 1 until arr.size) {
            val before = arr[i]
            val after = arr[j]
            val allowedAfter = allowed[before]
            val notAllowedAfter = notallowed[before]
            if (allowedAfter != null && !allowedAfter.contains(after)) {
                return false
            }
            if (notAllowedAfter != null && notAllowedAfter.contains(after)) {
                return false
            }
        }
    }
    return true
}


fun findMiddle(arr:IntArray):Int{
    return arr[arr.size/2]
}

fun main() {
    val strIn = prepareToString(inputFilePathDay5)

    val parts = strIn.split("\n\n")

    val rules = parts[0].lines()
    val updates = parts[1].lines()

//    println("rules:")
//    rules.forEach { println(it) }
//
//    println("updates:")
//    updates.forEach { println(it) }

    val allowed = mutableMapOf<Int, MutableSet<Int>>()
    val notallowed = mutableMapOf<Int, MutableSet<Int>>()

    for (rule in rules) {
        val (before, after) = rule.split("|").map { it.toInt() }
        if (!allowed.containsKey(before)) {
            allowed[before] = mutableSetOf()
        }
        allowed[before]?.add(after)
        if (!notallowed.containsKey(after)) {
            notallowed[after] = mutableSetOf()
        }
        notallowed[after]?.add(before)
    }

//    to2dArray(updates).forEach { row ->
//        println(row.joinToString(", "))

   // }
    var sum=0
    to2dIntArray(updates).forEach{ arr->
       if(isValidUpdate(arr,allowed,notallowed)){
           sum += findMiddle(arr)
       }
    }
    println(sum)

    println("allowed:")
    allowed.forEach { (key, value) ->
        println("$key -> ${value.joinToString(", ")}")
    }

    println("not allowed:")
    notallowed.forEach { (key, value) ->
        println("$key -> ${value.joinToString(", ")}")
    }

}