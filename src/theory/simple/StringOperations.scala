package theory.simple

object StringOperations extends App {

  private val str = "Scala course"

  println(str.split(" ").toList)
  println(str.startsWith("He"))
  println(str.replace("a", "").take(3).reverse)

  val num = "42".toInt
  println(num, num.getClass)

  val numStr = 42.toString
  println(numStr, numStr.getClass)

  // присоединение char!!! +:(начало) или :+ (конец)
  println('1'+:"42":+'3')

  var list = List(1, 2)
  println(list.appendedAll(List(3, 4)))
  println(List(1, 2) ++ List(3, 4))

  println(1 +: List(2, 3)) // List(1, 2, 3)
  println(List(1, 2) ++ List(3, 4)) // List(1, 2, 3, 4)
  println(List(1, 2) +: List(3, 4)) //List(List(1, 2), 3, 4)

  var varString = "Hello,"
  println(varString:++"ww")
  varString += " World!"
  println(varString)

  // Интерполяция строк
  val name = "Alla"
  println(s"Hello, $name")
  val surName = "Liashko"
  println(s"Hello, ${name+" "+surName}")

}
