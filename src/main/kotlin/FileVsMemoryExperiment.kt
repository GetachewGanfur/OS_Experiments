import java.io.File
import kotlin.system.measureNanoTime

fun main() {
    val size = 50 * 1024 * 1024 // 50MB
    val blockSize = 4 * 1024    // 4KB
    val data = ByteArray(blockSize) { 0x5A.toByte() }
    val file = File("./temp_test.bin")
    file.deleteOnExit()

    val fileWriteTime = measureNanoTime {
        file.outputStream().use { out ->
            var written = 0
            while (written < size) {
                out.write(data)
                written += blockSize
            }
        }
    }

    val fileReadTime = measureNanoTime {
        file.inputStream().use { input ->
            val buffer = ByteArray(blockSize)
            while (input.read(buffer) != -1) {
            }
        }
    }

    val memScanTime = measureNanoTime {
        var sum = 0L
        repeat(size / blockSize) {
            for (b in data) sum += b.toLong()
        }
    }

    println("File write : ${fileWriteTime / 1_000_000} ms")
    println("File read  : ${fileReadTime / 1_000_000} ms")
    println("Memory scan: ${memScanTime / 1_000_000} ms")
}