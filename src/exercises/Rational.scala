package exercises

/**
 * Рациональные числа
 */
// в круглых скобках параметры класса
class Rational(n: Int, d: Int) {
  // предусловие, проверяющее значение, передаваемое в конструктор (генерирует исключение)
  require(d != 0)
  // отладочное сообщение, помещается в конструктор класса
  // при создании объекта будет происходить вызов
  //println("Created " + n + "/" + d)
  // публичные поля поля класса
  val numer: Int = n
  val denom: Int = d

  override def toString = s"$numer/$denom"

  def add(that: Rational): Rational = new Rational(
    numer * that.denom + that.numer * denom,
    denom * that.denom
  )

  // this - позволяет сослаться на экземпляр объекта в отношении которого был вызван выполняемый метод
  // либо, если это конструктор - на создаваемый экземпляр объекта
  def lessThan(that: Rational): Boolean = this.numer * that.denom < that.numer * this.denom // в данном случае this не обязателен

  // в данном случае без this не обойтись
  def max(that: Rational): Rational = if(lessThan(that)) that else this

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

}
