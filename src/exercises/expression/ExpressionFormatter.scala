package exercises.expression

import exercises.markup2d.MarkupElement
import exercises.markup2d.MarkupElement.elem

/**
 * Форматирование арифметических выражений,
 * выводящее выражения в двумерной разметке.
 * Использует библиотеку {@link exercises.markup2d.MarkupElement}
 * <p>
 * Например:
 * </p><p>
 * выражение <b><i>x / (x + 1)</i></b><p>
 * выводится как :
 * </p>
 * <p>x</p><p>
 * -------
 * </p><p>
 *  x + 1
 *  </p>
 */
class ExpressionFormatter {

  /** Содержит операторы с нарастающей степенью приоритетности */
  private val operationGroup = Array(
    Set("|", "||"),
    Set("&", "&&"),
    Set("^"),
    Set("==", "!="),
    Set("<", "<=", ">", ">="),
    Set("+", "-"),
    Set("*", "%")
  )

  /** Отображение операторов на их степень приоритетности */
  private val precedence = {
    val assocs = for {
      i <- 0 until operationGroup.length
      op <- operationGroup(i)
    } yield op -> i
    assocs.toMap
  }

  /** самый высокий уровень приоритета */
  private val unaryPrecedence = operationGroup.length

  private val fractionPrecedence = -1

  private def format(e: Expression, enclPrec: Int): MarkupElement = e match {

    /* Если выражение является переменной то результат -> элемент из имени переменной */
    case ExpVar(name) => elem(name)

    /* Если выражение число -> элемент из числа */
    case ExpNumber(number) =>
      /* очищает выражение числа с плавающей точкой удалив окончание вида ".0" */
      def stripDot(s: String): String = {
        if (s endsWith ".0") s.substring(0, s.length - 2)
        else s
      }
      elem(stripDot(number.toString))

    /* Если выражение унарная операция -> то результат будет сформирован из оператора и аргумента
     * с самым высоким уровнем приоритета. Т.о. если аргумент arg является бинарной операцией,
     * то всегда будет отображаться в круглых скобках (кроме деления) */
    case UnaryOperation(operator, arg) => elem(operator) beside format(arg, unaryPrecedence)

    /* Если выражение имеет вид дроби -> промежуточный результат frac формируется путем помещения
    * отформатированных операндов left и right друг над друго с разделительным элементом.
    * Ширина линии равна максимальной ширине отформатированных операндов.
    * В случае, если дробь является аргументом еще одной функции, то по обе стороны frac добавляется пробел */
    case BinaryOperation("/", left, right) => {
      val top = format(left, fractionPrecedence)
      val bottom = format(right, fractionPrecedence)
      val line = elem('-', top.width max bottom.width, 1)
      val frac = top above line above bottom

      if (enclPrec != fractionPrecedence) frac
      else elem(" ") beside frac beside elem(" ")
    }

    /* Этот вариант применяется ко всем остальным бинарным операциям */
    case BinaryOperation(operator, left, right) => {
      val opPrec = precedence(operator)
      val l = format(left, opPrec)
      val r = format(right, opPrec + 1)
      val oper = l beside elem(" " + operator + " ") beside r

      if (enclPrec <= opPrec) oper
      else elem("(") beside oper beside elem(")")
    }
  }

  /** public метод */
  def format(e: Expression): MarkupElement = format(e, 0)

}
