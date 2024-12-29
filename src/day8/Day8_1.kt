package day8

import day4.prepareToList
import day6.Coordinate

val inputFilePathDay8="src/day8/test_input_day8"
val str8="............\n" +
        "........0...\n" +
        ".....0......\n" +
        ".......0....\n" +
        "....0.......\n" +
        "......A.....\n" +
        "............\n" +
        "............\n" +
        "........A...\n" +
        ".........A..\n" +
        "............\n" +
        "............"


fun getAntennas(input: List<String>): Map<Char, MutableSet<Coordinate>> {
    val regex = "[a-zA-Z0-9]".toRegex()
    val antennas = buildMap<Char, MutableSet<Coordinate>> {

        for (y in input.indices) {
            for (x in input[y].indices) {
                val chr = input[y][x]

                if (regex.matches(chr.toString())) {
                    val coordinates = this.getOrElse(chr) { mutableSetOf() }
                    coordinates.add(Coordinate(x, y))
                    this[chr] = coordinates
                }
            }
        }
    }
    return antennas
}
fun getDistance(a: Coordinate, b: Coordinate): Coordinate {
    return Coordinate(a.x - b.x, a.y - b.y)
}
fun multiply(a: Coordinate, scalar:Int): Coordinate {
    return Coordinate(a.x*scalar,a.y*scalar)
}
fun add(a: Coordinate, b: Coordinate): Coordinate {
    return Coordinate(a.x + b.x, a.y + b.y)
}
fun isInBounds(grid:List<String>, coordinate: Coordinate):Boolean{
    val mapXRange=grid.indices
    val mapYRange=grid[0].indices

    return coordinate.x in mapXRange && coordinate.y in mapYRange
}
fun findAntinodes(antenasWithCoordinates:Map<Char,Set<Coordinate>>, grid: List<String>): Set<Coordinate> {
    val resultset = buildSet {
        for (antennaType: Char in antenasWithCoordinates.keys) {
            for (first: Coordinate in antenasWithCoordinates[antennaType]!!) {
                for (second: Coordinate in antenasWithCoordinates[antennaType]!!) {
                    if (first == second) continue
                    val distance = getDistance(second,first)
                    val relDist= multiply(distance ,2)
                    val absAntNode= add(first,relDist)
                    if (isInBounds(grid,absAntNode)) {
                        add(absAntNode)
                    }
                }
            }
        }
    }
    return resultset
}
fun main() {
    val stringListOfLines = prepareToList(inputFilePathDay8)
    val antenasWithCoordinates= getAntennas(stringListOfLines)
    val finalResul= findAntinodes(antenasWithCoordinates,stringListOfLines)
    println("${finalResul.size}")
}

