fun prepareToCharArray(filepath: String): Array<CharArray> {
    val inputList = prepareToList(filepath)
    return inputList.map { it.toCharArray() }.toTypedArray()
}

fun findXmas(array: Array<CharArray>): Int {
    var count = 0

    for (x in array.indices) {
        for (y in array[x].indices) {
            if (array[x][y] == 'A' && checkXMAS(array, x, y)) {
                count++
            }
        }
    }

    return count
}

fun checkXMAS(array: Array<CharArray>, indexX: Int, indexY: Int): Boolean {
    val isInBounds = { x: Int, y: Int -> x in array.indices && y in array[x].indices }

    val conditions = listOf(
        { isInBounds(indexX - 1, indexY - 1) && isInBounds(indexX + 1, indexY - 1) && isInBounds(indexX - 1, indexY + 1) && isInBounds(indexX + 1, indexY + 1) &&
                array[indexX - 1][indexY - 1] == 'M' && array[indexX + 1][indexY - 1] == 'S' &&
                array[indexX - 1][indexY + 1] == 'M' && array[indexX + 1][indexY + 1] == 'S'
        },
        { isInBounds(indexX - 1, indexY - 1) && isInBounds(indexX + 1, indexY - 1) && isInBounds(indexX - 1, indexY + 1) && isInBounds(indexX + 1, indexY + 1) &&
                array[indexX - 1][indexY - 1] == 'S' && array[indexX + 1][indexY - 1] == 'M' &&
                array[indexX - 1][indexY + 1] == 'S' && array[indexX + 1][indexY + 1] == 'M'
        },
        { isInBounds(indexX - 1, indexY - 1) && isInBounds(indexX + 1, indexY - 1) && isInBounds(indexX - 1, indexY + 1) && isInBounds(indexX + 1, indexY + 1) &&
                array[indexX - 1][indexY - 1] == 'S' && array[indexX + 1][indexY - 1] == 'S' &&
                array[indexX - 1][indexY + 1] == 'M' && array[indexX + 1][indexY + 1] == 'M'
        },
        { isInBounds(indexX - 1, indexY - 1) && isInBounds(indexX + 1, indexY - 1) && isInBounds(indexX - 1, indexY + 1) && isInBounds(indexX + 1, indexY + 1) &&
                array[indexX - 1][indexY - 1] == 'M' && array[indexX + 1][indexY - 1] == 'M' &&
                array[indexX - 1][indexY + 1] == 'S' && array[indexX + 1][indexY + 1] == 'S'
        }
    )

    return conditions.any { it() }
}

fun main() {
    val inputArray = prepareToCharArray("src/input")

//    inputArray.forEach { row ->
//        println(row.joinToString(""))
//    }

    val xmasCount = findXmas(inputArray)
    println(xmasCount)
}
