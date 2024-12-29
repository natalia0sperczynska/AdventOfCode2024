package day6

import day4.prepareToCharArray

val inputFilePathDay6="src/day6/test_input_day6"
val str6="....#.....\n" +
        ".........#\n" +
        "..........\n" +
        "..#.......\n" +
        ".......#..\n" +
        "..........\n" +
        ".#..^.....\n" +
        "........#.\n" +
        "#.........\n" +
        "......#..."
enum class Direction(val dx: Int, val dy: Int) {
    NORTH(-1, 0),
    EAST(0, 1),
    SOUTH(1, 0),
    WEST(0, -1);

    fun turnRight(): Direction = values()[(ordinal + 1) % values().size]
}

data class Coordinate(var x:Int,var y:Int){
    override fun toString(): String {
        return "coordinates=(${x},${y})"
    }
}
class LaboratoryGuard(var position: Coordinate, var direction: Direction)
fun Array<CharArray>.toIntArrayWithCoordinates(guard: LaboratoryGuard): Pair<Array<IntArray>, MutableList<Pair<Int, Int>>> {
    val intArray = Array(this.size) { IntArray(this[0].size) }
    val coordinates = mutableListOf<Pair<Int, Int>>()

    for (i in this.indices) {
        for (j in this[i].indices) {
            when (this[i][j]) {
                '#' -> {
                    intArray[i][j] = 1
                    coordinates.add(i to j)
                }

                '^' -> {
                    guard.position = Coordinate(i, j)
                    guard.direction = Direction.NORTH
                }

                '>' -> {
                    guard.position = Coordinate(i, j)
                    guard.direction = Direction.EAST
                }

                'v' -> {
                    guard.position = Coordinate(i, j)
                    guard.direction = Direction.SOUTH
                }

                '<' -> {
                    guard.position = Coordinate(i, j)
                    guard.direction = Direction.WEST
                }

                '.' -> {
                    intArray[i][j] = 0
                }
            }
        }
    }
    return intArray to coordinates
}

fun simulateGuardMovement(
    map: Array<CharArray>,
    guard: LaboratoryGuard,
    visitedPositions: MutableSet<Pair<Int, Int>>
) {
    val maxX = map.size
    val maxY = map[0].size

    while (true) {
        val nextX = guard.position.x + guard.direction.dx
        val nextY = guard.position.y + guard.direction.dy

        if (nextX !in 0..<maxX || nextY !in 0..<maxY) break

        if (map[nextX][nextY] == '#') {
            guard.direction = guard.direction.turnRight()
        } else {
            guard.position.x = nextX
            guard.position.y = nextY
            visitedPositions.add(nextX to nextY)
        }
    }
}
fun main(){
    val charArray = prepareToCharArray(inputFilePathDay6)
    val guard = LaboratoryGuard(Coordinate(0, 0), Direction.NORTH)
    val visitedPositions = mutableSetOf<Pair<Int, Int>>()

    charArray.toIntArrayWithCoordinates(guard)
    visitedPositions.add(guard.position.x to guard.position.y)

    simulateGuardMovement(charArray, guard, visitedPositions)

    println("Distinct positions visited: ${visitedPositions.size}")
    visitedPositions.forEach { println("(${it.first}, ${it.second})") }
}
