package exercises.stringcompressor

import java.io.File

/**
 * Сжатие текста по алгоритму Хаффмана
 */
object Main extends App {

  val text = "SUSIE SAYS IT IS EASY"

  val compress = Compressor
  val compressingText = compress.code(new File("/home/gllllepulla/ScalaWorkSpace/Study/src/exercises/stringcompressor/exmpl.txt"))

  println("full length: " + compress.uncompressedLength)
  println("compressed length: " + compress.compressedLength)
  println("compressed in: " + compress.compressRate)

  val decompressingText = compress.decode(compressingText)
  println(decompressingText)

}
