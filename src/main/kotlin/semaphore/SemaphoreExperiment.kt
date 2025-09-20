package semaphore

import kotlin.concurrent.thread

fun main() {
    val sharedComputers = Semaphore(5)

    val users = List(10) { userId ->
        thread {
            println("사용자 $userId: 컴퓨터 사용 요청")

            sharedComputers.waitSemaphore()
            try {
                println("사용자 $userId: 컴퓨터 사용 시작")
                Thread.sleep((1000..2000).random().toLong())
                println("사용자 $userId: 컴퓨터 사용 종료")
            } finally {
                sharedComputers.signal()
            }
        }
    }

    users.forEach { it.join() }
}