package theory.func

import scala.io.Source

/**
 * Функции и замыкания
 */
object FunctionsAndClosures extends App {

  /**
   * Локальные функции
   * определение функции внутри другой функции
   */
  def processFile(fileName: String, width: Int): Unit = {
    // локальная функция
    def processLine(line: String): Unit = {
      if (line.length > width) println(fileName + ": " + line.trim)
    }

    val source = Source.fromFile(fileName)
    for (line <- source.getLines()) {
      processLine(line)
    }
  }

  /**
   * Функции первого класса
   * функциональные литералы, которые можно определить, вызвать или передать в качестве значений
   */
  var increase = (x: Int) => x + 1
  println(increase(10)) // 11

  increase = (x: Int) => x + 99
  println(increase(10)) // 109

  // функциональный блок
  increase = (x: Int) => {
    print("Hello ")
    print("World! ")
    x + 2
  }
  println(increase(10)) // Hello World! 12

  // метод получает функцию в качестве аргумента и вызывает ее в отношении каждого элемента списка
  val someNumbers = List(-11, -10, -5, 0, 5, 10)

  someNumbers.foreach((x: Int) => print(x)) // -11-10-50510
  val filteredList = someNumbers.filter((x: Int) => x > 0)
  // someNumbers.filter(x => x > 0)
  // someNumbers.filter(_ > 0)
  println(filteredList) // List(5, 10)

  /**
   * Частично примененные функции
   */
  def sum(a: Int, b: Int, c: Int): Int = a + b + c
  println(sum(1, 2, 3)) //6

  // функциональное значение, вызов получит 3 аргумента
  val partF = sum _
  println(partF(1, 2, 3)) // 6 - кратка форма a.apply(1, 2, 3)

  // функциональное значение, вызов получит 1 аргумент
  val part = sum(1, _: Int, 3)
  println(part(4)) // 8 (1 + 4 + 3)

  /**
   * Замыкания
   */
  var more = 1
  // функциональное значение, создаваемое во время выполнения "захватывает" переменную
  // это называется замыканием
  val addMore = (x: Int) => x + more
  println(addMore(11)) // 12

  more = 99
  println(addMore(11)) // 110

  // замыкание модифицирует переменную
  val someNum = List(-11, -10, -5, 0, 5, 10)
  var summ = 0
  someNum.foreach(summ += _)
  println(summ) // -11

  // при каждом вызове эта функция будет создавать новое замыкание и каждое замыкание
  // будет обращаться к той more, которая была активна на момент замыкания (захваченные параметры остаются в куче)
  def makeIncreaser(more: Int) = (x: Int) => x + more
  val inc1 = makeIncreaser(1)
  val inc99 = makeIncreaser(99)
  println(inc1(10)) // 11
  println(inc99(10)) // 109

}
