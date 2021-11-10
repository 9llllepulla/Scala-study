package exercises

import scala.annotation.tailrec

/**
 * Рациональные числа
 */
// первичный конструктор класса и единственная точка входа в класс, в круглых скобках параметры класса
class Rational(n: Int, d: Int) {
  // предусловие, проверяющее значение, передаваемое в конструктор (генерирует исключение)
  require(d != 0)
  // отладочное сообщение, помещается в конструктор класса
  // при создании объекта будет происходить вызов
  //println("Created " + n + "/" + d)

  /**
   * компилятор помещает коды инициализаторов полей класса в первичный конструктор в порядке их следования!
   */
  // приватное поле класса
  private val g = gcd(n.abs, d.abs)
  // публичные поля поля класса
  val numer: Int = n / g
  val denom: Int = d / g

  //  вспомогательный конструктор класса
  def this(n: Int) = this(n, 1)

  override def toString = s"$numer/$denom"

  def add(that: Rational): Rational = new Rational(
    this.numer * that.denom + that.numer * this.denom,
    this.denom * that.denom
  )

  // this - позволяет сослаться на экземпляр объекта в отношении которого был вызван выполняемый метод
  // либо, если это конструктор - на создаваемый экземпляр объекта
  def lessThan(that: Rational): Boolean = this.numer * that.denom < that.numer * this.denom // в данном случае this не обязателен

  // в данном случае без this не обойтись
  def max(that: Rational): Rational = if (lessThan(that)) that else this

  // приватный метод, вычисляющий наибольший общий делитель переданых значений
  @tailrec
  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a
    else gcd(b, a % b)
  }

  /**
   * переопределенные методы арифметических операций
   */
  def + (that: Rational): Rational = {
    new Rational(
      this.numer * that.denom + that.numer * this.denom,
      this.denom + that.denom)
  }

  def + (i: Int): Rational = new Rational(numer + i * denom, denom)

  def * (that: Rational): Rational = {
    new Rational(
      this.numer * that.numer,
      this.denom * that.denom)
  }

  def * (i: Int): Rational = new Rational(numer * i, denom)

  def - (that: Rational): Rational = {
    new Rational(numer * that.denom - that.numer * denom,
      denom * that.denom)
  }

  def - (i: Int): Rational = new Rational(numer - i * denom, denom)

  def / (that: Rational): Rational = new Rational(
    numer * that.denom,
    denom * that.numer)

  def / (i: Int): Rational = new Rational(numer, denom * i)

}

object App extends App {

  val x = new Rational(1, 5)
  println(x) // 1/5
  val oneHalf = new Rational(1, 2)
  println(oneHalf) // 1/2
  val twoThirds = new Rational(2, 3)
  println(twoThirds) // 2/3
  val sevenSix = oneHalf add twoThirds // or oneHalf.add(twoThirds)
  println(sevenSix) // 7/6
  // получаем доступ к публичным полям класса
  println(sevenSix.denom) // 6
  println(sevenSix.numer) // 7

  println(oneHalf lessThan twoThirds) // true
  println(twoThirds max sevenSix) // 7/6

  val whole = new Rational(6)
  println(whole) // 6/1

  val reduce = new Rational(6, 12)
  println(reduce) // 1/2

  // математические операции
  println(oneHalf + twoThirds) // 7/5
  println(twoThirds * sevenSix) // 7/9
  println(sevenSix / 3) // 7/18

}
