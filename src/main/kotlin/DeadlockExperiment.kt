fun main() {
    val lock1 = Any()
    val lock2 = Any()

    val t1 = Thread {
        synchronized(lock1) {
            Thread.sleep(100)
            synchronized(lock2) {
                println("Thread 1")
            }
        }
    }

    val t2 = Thread {
        synchronized(lock2) {
            Thread.sleep(100)
            synchronized(lock1) {
                println("Thread 2")
            }
        }
    }

    t1.start()
    t2.start()
    t1.join()
    t2.join()
}