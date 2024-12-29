package day9

import day5.prepareToString

val inputFilePathDay9="src/day9/test_input_day9"
val str9="2333133121414131402"
data class Segment(val len:Int, val id:Int){
    val isEmpty:Boolean get()=id==-1
}
fun parseInputToSegments(input: String): List<Segment> {
    val segments = mutableListOf<Segment>()
    var fileID = 0

    for ((index, char) in input.withIndex()) {
        if (!char.isDigit()) continue

        val length = char.digitToInt()
        val id = if (index % 2 == 0) {
            fileID++
        } else {
            -1
        }
        segments.add(Segment(length, id))
    }

    return segments
}
fun materializeSegments(segments: List<Segment>): MutableList<Int> {
    val materialized = mutableListOf<Int>()

    for (segment in segments) {
        repeat(segment.len) {
            materialized.add(segment.id)
        }
    }
    return materialized
}
fun compactDisk(materialized: MutableList<Int>) {
    while (true) {
        val firstOpenSpace = materialized.indexOfFirst { it == -1 }
        val lastUsedSpace = materialized.indexOfLast { it != -1 }

        if (firstOpenSpace >= lastUsedSpace) break

        materialized[firstOpenSpace] = materialized[lastUsedSpace]
        materialized[lastUsedSpace] = -1
    }
}
fun calculateChecksum(materialized: List<Int>): Long {
    var checksum = 0L
    for ((index, block) in materialized.withIndex()) {
        if (block != -1) {
            checksum += index * block
        }
    }
    return checksum
}

fun main(){
    val input= prepareToString(inputFilePathDay9)
    //println(input)
    val segments = parseInputToSegments(input)
    //println(segments)
    val materialized = materializeSegments(segments)
    //println(materialized)
    compactDisk(materialized)
    val checksum = calculateChecksum(materialized)
    println(checksum)
}
