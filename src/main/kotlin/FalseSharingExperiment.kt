import kotlin.concurrent.thread
import kotlin.system.measureNanoTime

@Volatile
private var x1 = 0L

@Volatile
private var x2 = 0L

fun main() {
    val iterations = 100_000_000

    val time = measureNanoTime {
        val t1 = thread {
            repeat(iterations) { x1++ }
        }

        val t2 = thread {
            repeat(iterations) { x2++ }
        }

        t1.join()
        t2.join()
    }

    println("False sharing time: ${time / 1_000_000} ms")
}