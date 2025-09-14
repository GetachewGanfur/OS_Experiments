package locality

import java.util.*
import kotlin.system.measureNanoTime

fun main() {
    val size = 10_000_000
    val array = IntArray(size) { 1 }
    var sum = 0L

    val sequentialTime = measureNanoTime {
        for(i in 0 until size) {
            sum += array[i]
        }
    }

    sum = 0L
    val rand = Random()
    val randomTime = measureNanoTime {
        repeat(size) {
            val idx = rand.nextInt(size)
            sum += array[idx]
        }
    }

    println("Sequential access: ${sequentialTime / 1_000_000} ms")
    println("Random access: ${randomTime / 1_000_000} ms")
}