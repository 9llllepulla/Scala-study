package theory.func

import scala.annotation.tailrec

/**
 * Функции
 */
object Functions extends App {

  private def getPerson(name: String, surName: String): String = {
    s"$name $surName"
  }
  println(getPerson("Alla", "Lyashko"))

  private def printPerson(name: String, surName: String): Unit = {
    println(s"$name $surName")
  }
  printPerson("Sergei", "Lyashko")

  def createFuncWithDefaultParameters(x: Int, y: String = "Default y"): String = {
    s"x = $x and y = $y"
  }
  println(createFuncWithDefaultParameters(42))

  // Вложенные функции
  def callBossFunction(): String = {
    def callHelpFunction(): String = "Help !"
    // somecode
    callHelpFunction()
  }
  println(callBossFunction())

  // примеры способов вызова функции
  // # 1
  val strlen1 = (str: String) => str.length
  // # 2
  val strlen2: String => Int = (str: String) => str.length
  // # 3
  val strlen3: (String) => Int = (str) => str.length
  // # 4
  val strlen4: (String) => Int = _.length

  // Функции высшего порядка ////////////////

  //////////// Способ 1
  // функция 1
  @tailrec
  def nTimes(function: Int => Int, x: Int, n: Int): Int = {
    if (n <= 0) x
    else nTimes(function, function(x), n - 1)
  }

  // В качестве функции, которую будет передавать в качестве аргумента, возьмем функцию, увеличивающую число на единицу:
  val increment: Int => Int = (x: Int) => x + 1
  // вызов функции
  println(nTimes(increment, 0, 3)) // 3

  //////////// Способ 2
  /**
   * Через каррирование
   * Выглядеть это теперь будет так:
   * def имяФункции(аргумент1, аргумент2) = (аргумент3) => операция
   */
  def curruingNTimes(function: Int => Int, n: Int): Int => Int = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => curruingNTimes(function, n - 1)(function(x))
  }
  // вызов
  println(curruingNTimes(increment, 3)(0))

  def sum: Int => Int => Int = (x: Int) => (y: Int) => x + y

  // или
  def sum2: Int => Int => Int = x => y => x + y

  // или
  def sum3 = (x: Int) => (y: Int) => x + y
  //println(sum(2)(3))

  def someFunc: Int => Function1[Int, Int] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val res = someFunc(1)
  println(res(4))

  /**
   * Частичные функции
   */
  val aPartialFunction: PartialFunction[String, String] = {
    case "mon" => "Work!"
    case "fri" => "Party Time"
    case "sun" => "Relax a little"
  }

  // Можно заранее узнать, допустимо ли использовать аргумент в функции или нет.
  println(aPartialFunction.isDefinedAt("tue")) // false

  // Объединить несколько функций в цепь нам поможет orElse:
  val partialChain: PartialFunction[String, String] = aPartialFunction.orElse[String, String] {
    case "sat" => "it's just a Saturday"
  }
  println(partialChain("mon")) // Work!
  println(partialChain("sat")) // it's just a Saturday

  // лифтинг позволяет поднять частичную функцию на следующий уровень.
  // После lift - функция будет возвращать значения типа Option (т.е. решается проблема MatchError)
  val lifted = aPartialFunction.lift // теперь на выходе имеем тип Option[String]
  println(lifted("fri")) // Some(Party Time)
  println(lifted("thu")) // None
}
