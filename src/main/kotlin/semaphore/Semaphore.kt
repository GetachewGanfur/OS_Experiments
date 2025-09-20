package semaphore

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class Semaphore(
    private var permits: Int,
) {
    private val lock = ReentrantLock()
    private val condition = lock.newCondition()

    private val waitQueue = mutableListOf<Thread>()

    fun waitSemaphore() {
        lock.withLock {
            val currentThread = Thread.currentThread()
            waitQueue.add(currentThread)

            while (permits <= 0 || waitQueue.first() != currentThread) {
                condition.await()
            }

            waitQueue.removeFirst()
            permits--
        }
    }

    fun signal() {
        lock.withLock {
            permits++
            condition.signal()
        }
    }
}