package mutex

class ReentrantSimpleMutex : Mutex {
    private var locked = false
    private var owner: Thread? = null
    private var holdCount = 0
    private val monitor = Object()

    override fun lock() {
        val current = Thread.currentThread()
        synchronized(monitor) {
            while (locked && owner != current) {
                monitor.wait()
            }
            locked = true
            owner = current
            holdCount++
        }
    }

    override fun unlock() {
        val current = Thread.currentThread()
        synchronized(monitor) {
            if (owner != current) {
                throw IllegalMonitorStateException("현재 스레드가 락을 보유하고 있지 않습니다.")
            }
            holdCount--
            if (holdCount == 0) {
                locked = false
                owner = null
                monitor.notify()
            }
        }
    }

    override fun tryLock(): Boolean {
        val current = Thread.currentThread()
        synchronized(monitor) {
            return if (!locked || owner == current) {
                locked = true
                owner = current
                holdCount++
                true
            } else {
                false
            }
        }
    }
}