package theory.caseclasses

/**
 * У запечатанного класса не может быть других подклассов,
 * кроме тех, что определены в этом же файле.
 *
 * Для case-классов обеспечивается улучшенная поддержка компилятором
 *
 */
sealed abstract class Expression

/**
 * В case classes добавляются по-умолчанию:
 * <p>
 *    -- фабричный метод;
 * </p><p>
 *    -- все аргументы получают префикс val и к ним можно обращаться как полям;
 * </p><p>
 *    -- класс получает реализацию toString. hashCode, equals.
 * </p><p>
 *    -- компилятор добавляет метод <i><b>copy</i></b> к классу для его изменения
 */
case class Var(name: String) extends Expression

case class Number(number: Double) extends Expression

case class UnaryOperation(operator: String, arg: Expression) extends Expression

case class BinaryOperation(operator: String, left: Expression, right: Expression) extends Expression

object Expr extends App {

  val newVar = Var("x") // new Var("x")

  val op = BinaryOperation("+", Number(1), newVar) // фабричные методы создания объектов
  println(op) // BinaryOperation(+,Number(1.0),Var(x))

  println(newVar.name) // x
  println(op.left) // Number(1.0)

  val opCopy = op.copy(operator = "-")
  println(opCopy) // BinaryOperation(-,Number(1.0),Var(x))

  /* Case classes поддерживают сопоставления с образцом */

  def simplifyTop(expression: Expression): Expression = expression match {
    case UnaryOperation("-", UnaryOperation("-", e)) => e // двойное отрицание
    case BinaryOperation("+", e, Number(0)) => e // прибавление 0
    case BinaryOperation("*", e, Number(1)) => e // умножение на 1
    case _ => expression
  }

  def describe(exp: Expression): String = exp match {
    case Number(_) => "число"
    case Var(_) => "переменная"
  }

  println(describe(Number(0)))
  //println(describe(UnaryOperation("/", Var("y")))) // scala.MatchError

}


