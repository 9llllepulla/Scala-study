package theory.simple

object TypesValuesVariables extends App {

  var varString = "var"

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
