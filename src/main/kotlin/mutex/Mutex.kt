package mutex

interface Mutex {
    fun lock()
    fun unlock()
    fun tryLock(): Boolean

    fun <T> withLock(block: () -> T): T {
        lock()
        try {
            return block()
        } finally {
            unlock()
        }
    }
}