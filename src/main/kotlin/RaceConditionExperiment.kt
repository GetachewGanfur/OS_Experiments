import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.concurrent.thread

var counter = 0

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

    mutex()
}

suspend fun mutex() = coroutineScope {
    counter = 0
    val mutex = Mutex()

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