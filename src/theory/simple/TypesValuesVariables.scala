package theory.simple

object TypesValuesVariables extends App {

  // изменяемые переменные
  var varString = "var"
  // неизменяемые переменные
  val valString = "val"
  // отложено-инициализированная неизменяемая переменная, вычисляемая в первый раз ссылки на нее
  // позволяет ссылаться на переменные, объявленные позже
  lazy val lazyVal = "lazyVal" + x
  val x = "x"
  // переменная как метод без параметров, вычисляется каждый раз, когда на него ссылаемся
  def defVar = "defVar"

  val hello: String = "Hello"
  val ch: Char = 'C'
  val num = 32
  val dec = 1.56
  val boo: Boolean = true
  val longNum = 12L
  val floatNum = 2f
  println(hello, ch, num, dec, boo, longNum+" is a "+longNum.getClass, floatNum, varString)

  varString = "new var"
  println(hello, ch, num, dec, boo, longNum+" is a "+longNum.getClass, floatNum, varString)

  /**
  * Булевы значения
   * 'eq' - проверка на равенство ссылок
   * '==' - проверка на равенство значений
  */
  val s03 = "bar"
  val s13 = "foo" + s03
  val s23 = "foo" + s03
  println(s13 == s23) // true

  val s14 = "foo";
  val s24 = "foo";
  println(s14 == s24) // true

  // проверка ссылок
  val s3 = "bar";
  val s1 = "foo" + s3;
  val s2 = "foo" + s3;
  println(s1 eq s2) // false

  // Так как В Java все константные строки интернированы: компилятор производит оптимизацию, в результате чего,
  // одинаковые строковые литералы ссылаются на один и тот же объект в памяти.
  val s11 = "foo";
  val s21 = "foo";
  println(s11 eq s21) // true

}
