import mutex.SimpleMutex

fun main() {
    val mutex1 = SimpleMutex()
    val mutex2 = SimpleMutex()

    val t1 = Thread {
        println("Thread 1: mutex1 lock 시도")
        mutex1.lock()
        println("Thread 1: mutex1 획득")

        println("Thread 1: mutex2 lock 시도")
        mutex2.lock()
        println("Thread 1: mutex2 획득")

        mutex2.unlock()
        mutex1.unlock()
        println("Thread 1: 종료")
    }

    val t2 = Thread {
        println("Thread 2: mutex2 lock 시도")
        mutex2.lock()
        println("Thread 2: mutex2 획득")

        println("Thread 2: mutex1 lock 시도")
        mutex1.lock()
        println("Thread 2: mutex1 획득")

        mutex1.unlock()
        mutex2.unlock()
        println("Thread 2: 종료")
    }

    t1.start()
    t2.start()
    t1.join()
    t2.join()
}


//fun main() {
//    val lock1 = Any()
//    val lock2 = Any()
//
//    val t1 = Thread {
//        synchronized(lock1) {
//            Thread.sleep(100)
//            synchronized(lock2) {
//                println("Thread 1")
//            }
//        }
//    }
//
//    val t2 = Thread {
//        synchronized(lock2) {
//            Thread.sleep(100)
//            synchronized(lock1) {
//                println("Thread 2")
//            }
//        }
//    }
//
//    t1.start()
//    t2.start()
//    t1.join()
//    t2.join()
//}