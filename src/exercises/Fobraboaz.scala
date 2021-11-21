package exercises

import scala.io.StdIn.readLine

object Fobraboaz extends App {

  /**
   * Считайте из консоли два числа: startIndex и endIndex.
   * Они расположены в первой строке и разделены одним пробелом.
   * Далее считайте строку str.
   * Напечатайте в ответ входную строку,
   * обратив порядок символов от startIndex до endIndex включительно.
   *
   * Sample Input:
   *    2 6
   *    foobarbaz
   *
   * Sample Output:
   *    fobraboaz
   */

  def partReverse(word: String, diapason: String): String = {
    val edges = diapason.split(" ").map(s => s.toInt)

    def reverse(): String = {
      word.substring(edges(0), edges(1)+1).reverse
    }

    word.dropRight(word.length - edges(0)) + reverse() + word.substring(edges(1)+1)
  }

  println(partReverse(diapason = readLine(), word = readLine()))

}
