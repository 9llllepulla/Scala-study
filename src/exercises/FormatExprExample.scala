package exercises

import exercises.expression._

/**
 * Форматирование выражений
 * */
object FormatExprExample extends App {

  val formatter = new ExpressionFormatter

  val eOne = BinaryOperation("*", BinaryOperation("/", ExpNumber(1), ExpNumber(2)),
                                        BinaryOperation("+", ExpVar("x"), ExpNumber(1)))

  val eTwo = BinaryOperation("+", BinaryOperation("/", ExpVar("x"), ExpNumber(2)),
                                        BinaryOperation("/", ExpNumber(1.5), ExpVar("x")))

  val eTree = BinaryOperation("/", eOne, eTwo)

  def show(e: Expression) = s"${println(formatter.format(e))}\n\n"

  //for (e <- Array(eOne, eTwo, eTree)) show(e)
  show(eTree)

}
