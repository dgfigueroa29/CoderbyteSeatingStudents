fun seatingStudents(line: String?): Int {
    val list = mutableListOf<Int>()
    val arr = line?.split(", ")?.toMutableList() ?: mutableListOf()
    arr.forEach { list.add(it.toInt()) }
    val totalSeats = list[0]
    list.removeAt(0)
    val map: MutableMap<Int, Pair<Boolean, Boolean>> = mutableMapOf()

    for (x in 0 until totalSeats) {
        val currentSeat = x + 1
        val below = currentSeat + 2
        val leftOrRight = if (currentSeat % 2 == 0) {
            currentSeat - 1
        } else {
            currentSeat + 1
        }

        if (list.contains(currentSeat)) {
            map[currentSeat] = Pair(first = false, second = false)
        } else {
            val horizontalValue = if (leftOrRight > totalSeats) {
                false
            } else {
                list.contains(leftOrRight).not()
            }
            val verticalValue = if (below > totalSeats) {
                false
            } else {
                list.contains(below).not()
            }

            map[currentSeat] = Pair(horizontalValue, verticalValue)
        }
    }

    println(map)

    if (map[totalSeats] == map[totalSeats - 1]) {
        map[totalSeats] = Pair(first = false, second = false)
    }

    val mapFiltered = map.filter { it.value.first || it.value.second }
    println(mapFiltered)
    return mapFiltered.size
}

fun main() {
    println(seatingStudents(readLine()))
}