package theory.constructions

object FactoryMethod extends App {

  val numA = new Number(1)
  val numB = new Number(2)

  val numC = Number(numA, numB) // применяем apply

  println(numA.num) // 1
  println(numB.num) // 2
  println(numC.num) // 3

}
class Number(val num: Int)

object Number {
  val Pi = 3.14

  def apply(x: Number, y: Number): Number = new Number(x.num + y.num)
}
