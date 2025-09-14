import java.util.concurrent.ArrayBlockingQueue
import kotlin.concurrent.thread

fun main() {
    val queue = ArrayBlockingQueue<Int>(5)

    val producer = thread {
        for (i in 1..10) {
            queue.put(i)
            println("Produce $i")
        }
    }

    val consumer = thread {
        repeat(10) {
            val item = queue.take()
            println("Consume $item")
        }
    }

    producer.join()
    consumer.join()
}
