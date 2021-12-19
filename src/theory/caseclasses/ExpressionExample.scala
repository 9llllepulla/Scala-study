package theory.caseclasses

/**
 * У запечатанного класса не может быть других подклассов,
 * кроме тех, что определены в этом же файле.
 *
 * Для case-классов обеспечивается улучшенная поддержка компилятором
 *
 */
sealed abstract class ExpressionExample

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
case class VarExample(name: String) extends ExpressionExample

case class NumberExample(number: Double) extends ExpressionExample

case class UnOpExample(operator: String, arg: ExpressionExample) extends ExpressionExample

case class BiOpExample(operator: String, left: ExpressionExample, right: ExpressionExample) extends ExpressionExample

object Example extends App {

  val newVar = VarExample("x") // new Var("x")

  val op = BiOpExample("+", NumberExample(1), newVar) // фабричные методы создания объектов
  println(op) // BinaryOperation(+,Number(1.0),Var(x))

  println(newVar.name) // x
  println(op.left) // Number(1.0)

  val opCopy = op.copy(operator = "-")
  println(opCopy) // BinaryOperation(-,Number(1.0),Var(x))

  /* Case classes поддерживают сопоставления с образцом */

  def simplifyTop(expression: ExpressionExample): ExpressionExample = expression match {
    case UnOpExample("-", UnOpExample("-", e)) => e // двойное отрицание
    case BiOpExample("+", e, NumberExample(0)) => e // прибавление 0
    case BiOpExample("*", e, NumberExample(1)) => e // умножение на 1
    case _ => expression
  }

  def describe(exp: ExpressionExample): String = exp match {
    case NumberExample(_) => "число"
    case VarExample(_) => "переменная"
  }

  println(describe(NumberExample(0)))
  //println(describe(UnaryOperation("/", Var("y")))) // scala.MatchError

}


