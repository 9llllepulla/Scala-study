package theory.caseclasses

/**
 * <i>Разновидности паттернов</i>
 *
 */
object PatternMatching extends App {

  /* Подстановочные паттерны */
   val expression: Any = expression match {
    case BiOpExample(_, _, _) => println(s"$expression является бинарной операцией") //  -> (_)
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
    case BiOpExample("+", e, NumberExample(0)) => println("глубокое соответствие") // проверка объекта и его содержимого
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
    case UnOpExample("abc", e @ UnOpExample("abc", _)) => e // в случае совпадения переменной присваивается
                                                                  // соответствующий объект
    case _ =>
  }

  /** Ограждение образца (pattern guard) */
  def simplifyAdd(e: ExpressionExample): Any = e match {
    case BiOpExample("+", x, y) if x == y => BiOpExample("*", x, NumberExample(2)) // if - ограничитель
    case _ =>
  }

  /** Паттерны в определениях переменных */
  val myTuple = (123, "abc")
  val (number, string) = myTuple
  println(number) // 123
  println(string) // abc

  val exampleExp = BiOpExample("*", NumberExample(5), NumberExample(1))
  val BiOpExample(operator, left, right) = exampleExp
  println(operator) // *
  println(left) // Number(5.0)
  println(right) // Number(1.0)

  /** Последовательности вариантов в качестве частично примененных функций */
  val withDefault: Option[Int] => Int = {
    case Some(x) => x
    case None => 0
  }
  println(withDefault(Some(10))) // 10
  println(withDefault(None)) // 0

  val secondElement: List[Int] => Int = {
    case x :: y :: _ => y
  }
  println(secondElement(List(5, 6, 7))) // 6

  /* второй вариант определения частично примененной функции с возможностью проверки определения */
  val secondElement2: PartialFunction[List[Int], Int] = {
    case x :: y :: _ => y
  }
  println(secondElement2.isDefinedAt(List(3, 4, 6))) // true
  println(secondElement2.isDefinedAt(List(1))) // false

  /** Паттерны в выражениях for */
  val capitals = new OptionalExample().countryCapitalsMap
  for ((country, city) <- capitals)
    println("Столицей " + country + " является " + city)
  // Столицей France является Paris
  // Столицей Japan является Tokyo
  // Столицей Russia является Moscow

  val fruits = List(Some("apple"), None, Some("orange")) // происходит отбор элементов соответствующих паттерну
  for (Some(fruit) <- fruits) print(fruit + " ") // apple orange

}
