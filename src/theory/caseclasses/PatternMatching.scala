package theory.caseclasses

/**
 * <i>Разновидности паттернов</i>
 *
 */
object PatternMatching extends App {

  /* Подстановочные паттерны */
   val expression: Any = expression match {
    case BinaryOperation(_, _, _) => println(s"$expression является бинарной операцией") //  -> (_)
    case _ => println("Это что-то другое")
  }

  /* Паттерны - константы */
  def describe(x: Any): String = x match {
    case 5 => "пять"
    case true => "правда"
    case "hello" => "привет!"
    case Nil => "пустой список"
    case _ => "что-то другое"
  }
  println(describe(Nil)) // пустой список

  /** Паттерн - переменная */
  val expr: Any = expr match {
    case 0 => "нуль"
    case somethingElse => "не нуль: " + somethingElse // привязывает переменную к объекту
  }

  /** Паттерн - конструктор */
  val exp: Any = exp match {
    case BinaryOperation("+", e, Number(0)) => println("глубокое соответствие") // проверка объекта и его содержимого
    case _ =>
  }

  /** Паттерны - последовательности */
  val express: Any = express match {
    case List(0, _, _) => println("соответствие найдено") // проверка, что список из 3 элементов начинается с 0
    case List(0, _*) => println("соответствие найдено") // проверка последовательности произвольной длины
    case _ =>
  }

  /** Паттерны - кортежи */
  def tupleDemo(expr: Any) = expr match {
    case (a, b, c) => println("соответствует " + a + b + c) // соответствие произвольному кортежу из 3-х элементов
    case _ =>
  }
  tupleDemo(("a ", 3, "-tuple")) // соответствует a 3-tuple

  /** Типизированные паттерны (typed pattern) */
  def generalSize(x: Any): Int = x match { // можно использовать в качестве заменителя проверок и приведения типов
    case s: String => s.length
    case m: Map[_, _] => m.size
    case _ => -1
  }
  println(generalSize("abc")) // 3
  println(generalSize(Map(1 -> 'a', 2 -> 'b'))) // 2
  println(generalSize(math.Pi)) // -1

  /** Затирание типов */
  def isIntIntMap(x: Any): Any = x match {
    case m: Map[Int, Int] => true
    case _ =>
  }
  println(isIntIntMap(Map(1 -> 1))) // true
  println(isIntIntMap(Map("abc" -> "abc"))) // true FIXME <- из-за затирания типов проверка не верная !!

  def isStringArray(x: Any): Any = x match {
    case a: Array[String] => "yes"
    case _ => "no"
  }
  println(isStringArray(Array("abc"))) // yes
  println(isStringArray(Array(1, 2, 3))) // no TODO <- В массивах не происходит затирания типов !!!

  /** Привязка переменной */
  val exprs: Any = exprs match {
    case UnaryOperation("abc", e @ UnaryOperation("abc", _)) => e // в случае совпадения переменной присваивается
                                                                  // соответствующий объект
    case _ =>
  }

  /** Ограждение образца (pattern guard) */
  def simplifyAdd(e: Expression): Any = e match {
    case BinaryOperation("+", x, y) if x == y => BinaryOperation("*", x, Number(2)) // if - ограничитель
    case _ =>
  }
}
