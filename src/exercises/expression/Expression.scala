package exercises.expression

sealed abstract class Expression

case class ExpVar(name: String) extends Expression

case class ExpNumber(number: Double) extends Expression

case class UnaryOperation(operator: String, arg: Expression) extends Expression

case class BinaryOperation(operator: String, left: Expression, right: Expression) extends Expression

