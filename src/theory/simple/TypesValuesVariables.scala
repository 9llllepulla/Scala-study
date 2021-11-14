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

}
