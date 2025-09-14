package locality

import kotlin.system.measureNanoTime

fun main() {
    val n = 2000
    val matrix = Array(n) { IntArray(n) { 1 } }
    var sum = 0L

    val rowTime = measureNanoTime {
        for(i in 0 until n) {
            for(j in 0 until n) {
                sum += matrix[i][j]
            }
        }
    }

    sum = 0L

    val colTime = measureNanoTime {
        for(i in 0 until n) {
            for(j in 0 until n) {
                sum += matrix[j][i]
            }
        }
    }

    println("Row time: ${rowTime / 1_000_000}ms")
    println("Col time: ${colTime / 1_000_000}ms")
}