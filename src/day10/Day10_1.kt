package day10
import day4.prepareToList
import day6.Coordinate

val inputFilePathDay10="src/day10/test_input_day10"
val str10="2333133121414131402"

fun buildMapFromInput(input: List<String>): Map<Coordinate, Int> {
    val map = mutableMapOf<Coordinate, Int>()
    for (y in input.indices) {
        for (x in input[y].indices) {
            val height = input[y][x].digitToInt()
            map[Coordinate(x, y)] = height
        }
    }
    return map
}

fun Map<Coordinate, Int>.getHeight(coord: Coordinate): Int {
    return this.getOrDefault(coord, Int.MAX_VALUE)
}

fun countReachableSummits(trailhead: Coordinate, map: Map<Coordinate, Int>): Int {
    if (map[trailhead] != 0) {
        throw IllegalArgumentException()
    }

    fun explore(current: Coordinate, visited: MutableSet<Coordinate>): Set<Coordinate> {
        if (current in visited) return emptySet()
        visited.add(current)

        val currentHeight = map.getHeight(current)
        if (currentHeight == 9) return setOf(current)

        val nextHeight = currentHeight + 1
        val summits = mutableSetOf<Coordinate>()

        for (neighbor in current.neighbors()) {
            if (map.getHeight(neighbor) == nextHeight) {
                summits.addAll(explore(neighbor, visited))
            }
        }

        return summits
    }

    return explore(trailhead, mutableSetOf()).size
}


fun main() {
    val input = prepareToList(inputFilePathDay10)
    val map = buildMapFromInput(input)

    val trailheads = map.filter { it.value == 0 }.keys

    var totalSummits = 0
    for (trailhead in trailheads) {
        totalSummits += countReachableSummits(trailhead, map)
    }

    println("Total reachable summits: $totalSummits")
}

//czy da sie jakos inaczej sprawdzic czy jestesmy dlaej w polu planszy niz ta funkcja? fun Map<Coordinate, Int>.getHeight(coord: Coordinate): Int {
//    return this.getOrDefault(coord, Int.MAX_VALUE)
//}  bardziej w tym stylu fun isInBounds(grid:List<String>, coordinate: Coordinate):Boolean{
//    val mapXRange=grid.indices
//    val mapYRange=grid[0].indices
//
//    return coordinate.x in mapXRange && coordinate.y in mapYRange
//}
