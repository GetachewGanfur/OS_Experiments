package mutex

// 이 경우엔 같은 스레드가 다시 lock 하면 데드락 발생.
class SimpleMutex : Mutex {
    private var locked = false
    private val monitor = Object()

    override fun lock() {
        synchronized(monitor) {
            while (locked) {
                monitor.wait()
            }
        }

        locked = true
    }

    override fun unlock() {
        synchronized(monitor) {
            locked = false

            monitor.notify()
        }
    }

    override fun tryLock(): Boolean {
        synchronized(monitor) {
            return if (!locked) {
                locked = true
                true
            } else {
                false
            }
        }
    }
}