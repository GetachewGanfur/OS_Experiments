import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread
import kotlin.system.measureNanoTime

fun main() {
    val tasks = 100_000

    val threadTime = measureNanoTime {
        val threads = List(tasks) {
            thread(start = true) {}
        }
        threads.forEach { it.join() }
    }

    val coroutineTime = measureNanoTime {
        runBlocking {
            val jobs = List(tasks) {
                launch {}
            }
            jobs.forEach { it.join() }
        }
    }

    println("Thread Time: ${threadTime / 1_000_000} ms")
    println("Coroutines Time: ${coroutineTime / 1_000_000} ms")
}