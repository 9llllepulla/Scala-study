package exercises

import scala.io.Source

/**
 * Считывает строки из файла и выводит их на стандартное устройство, предваряя каждую строку
 * количеством содержащихся в ней символов.
 */
object LineReader {

  def main(args: Array[String]): Unit = {

    if(args.length > 0) {

      val lines = Source.fromFile(args(0)).getLines().toList

      val longestLine = lines.reduceLeft((a, b) => if(a.length > b.length) a else b)

      val maxWidth = widthOfLength(longestLine)

      for(line <- lines) {
        val numSpace = maxWidth - widthOfLength(line)
        val padding = " " * numSpace
        println((padding + line.length + " | " + line))
      }

    }
    else Console.err.println("Введите имя файла: ")
  }

  def widthOfLength(s: String): Int = s.length.toString.length

}
