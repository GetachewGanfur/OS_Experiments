import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import mutex.ReentrantSimpleMutex
import kotlin.concurrent.thread

private var counter = 0

suspend fun main(): Unit {
//    val lock = Any()

    val threads = List(10) {
        thread {
            repeat(10000) {
//                synchronized(lock) {
                counter++
//                }
            }
        }
    }
    threads.forEach { it.join() }

    println("기대값: 100000, 실제값: $counter")

    counter = 0
    mutex()
}

suspend fun mutex() = coroutineScope {
    val mutex = ReentrantSimpleMutex()

    val jobs = List(10) {
        launch(Dispatchers.Default) {
            repeat(10000) {
                mutex.withLock {
                    counter++
                }
            }
        }
    }

    jobs.forEach { it.join() }
    println("기대값: 100000, 실제값: $counter")
}