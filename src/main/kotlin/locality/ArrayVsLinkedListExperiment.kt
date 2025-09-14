package locality

import java.util.*
import kotlin.system.measureNanoTime

fun main() {
    val size = 5_000_00
    val arr = IntArray(size) { it }
    val list = LinkedList<Int>().apply {
        for (i in 0 until size) add(i)
    }
    var sum = 0L

    val arrTime = measureNanoTime {
        for (x in arr) sum += x
    }

    sum = 0L

    val linkTime = measureNanoTime {
        for (x in list) sum += x
    }

    println("Array traversal: ${arrTime / 1_000_000} ms")
    println("LinkedList traversal: ${linkTime / 1_000_000} ms")
}