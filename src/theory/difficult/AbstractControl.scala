package theory.difficult

import java.io.{File, PrintWriter}
import java.util.Date

/**
 * Управляющие абстракции
 */
object AbstractControl extends App {

  private def filesHere: Array[File] = new File(".").listFiles

  // функция высшего порядка (принимает другую функцию)
  def filesMatching(matcher: String => Boolean): Array[File] = {
    for(file <- filesHere; if matcher(file.getName))
      yield file
  }

  // три поисковых метода, пользующиеся вспомогательным методом
  def filesEnding(query: String): Array[File] = filesMatching(_.endsWith(query)) // отбор по окончанию файла

  def filesContaining(query: String): Array[File] = filesMatching(_.contains(query)) // отбор по содержанию в имени файла

  def filesRegex(query: String): Array[File] = filesMatching(_.matches(query)) // поиск по регулярке

  // exsists - управляющая абстракция, предоставляемая библиотекой Scala
  def containsNeg(nums: List[Int]): Boolean = nums.exists(_ < 0)
  println(containsNeg(List(0, 1, -2, 3))) // true

  /**
   * Каррирование:
   * f1 = f(x)
   * f2 = f1(y)
   * result = f2(z)
   * Иначе говоря:
   * result = f(x)(y)(z)
   */
  def sum(x: Int, y: Int): Int = x + y
  println(sum(1, 2)) // 3
  // или
  def funcSum(x: Int): Int => Int = (y: Int) => x + y
  println(funcSum(1)(2)) // 3
  // или
  def curriedSum(x: Int)(y: Int): Int = x + y
  println(curriedSum(1)(2)) // 3

  // частично примененная функция ( _ является заместителем для второго значения)
  val onePlus = curriedSum(1)_
  println(onePlus(2)) // 3
  val twoPlus = curriedSum(2)_
  println(twoPlus(3)) // 5

  /**
   * Создание управляющих конструкций
   */
  // Открытие ресурса, работа с ним и закрытие
  def withPrintWriter(file: File, op: PrintWriter => Unit): Unit = {
    val writer = new PrintWriter(file)
    try{
      op(writer)
    } finally {
      writer.close()
    }
  }
  // можно воспользоваться как (в данном случае закрытие файла в конце работы гарантируется методом withPrintWriter
  // эта технология называется "Шаблон временного пользования", т.к. функция открывает ресурс и отдает его вов временное
  // пользование функции ор
  withPrintWriter(new File("date.txt"), writer => writer.println(new Date()))

  // можно переделать вызов этой функции чтобы она была похожа на вызов управляющей конструкции
  def withPrintWriter2(file: File)(op: PrintWriter => Unit): Unit = {
    val writer = new PrintWriter(file)
    try{
      op(writer)
    } finally {
      writer.close()
    }
  }
  // вызов
  val file = new File("date.txt")
  withPrintWriter2(file) {
    writer => writer.println(new Date())
  }
}
