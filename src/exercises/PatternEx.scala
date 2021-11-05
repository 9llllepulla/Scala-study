package exercises

import scala.io.StdIn.readLine

object PatternsEx extends App {

  /**
  Давайте напишем метод guard, который получает на вход два параметра: data и maxLength:

    data - это то, что мы скармливаем методу на проверку. Это может быть все, что угодно: число, строка, список, вектор - но что именно, вам заранее не известно
    maxLength - максимально допустимая длина строки или списка
    типом возвращаемого значения для guard является String
    Внутри метода необходимо прописать шаблон, состоящий из пяти case. Результаты проверок на соответствие шаблону следующие:

    Задан список List допустимой длины
    Длина списка больше максимально допустимого значения
    Длина строки не превышает максимально допустимого значения
    Получена строка недопустимой длины
    Что это? Это не строка и не список
   */
  def guard(data: Any, maxLength: Int): String = {
    data match {
      case someList: List[Any] => {
        if(someList.length <= maxLength) "Задан список List допустимой длины"
        else "Длина списка больше максимально допустимого значения"
      }
      case someString: String => {
        if(someString.length <= maxLength) "Длина строки не превышает максимально допустимого значения"
        else "Получена строка недопустимой длины"
      }
      case _ => "Что это? Это не строка и не список"
    }
  }
  // Test
  println(guard(List("43", 34, 43, "ewe", 'w'), 4))
  println(List("43", 34, 43, "ewe", 'w').length)

  // ([a-zA-Z]+)[\s]{1}([a-zA-Z]+)


  val inputName = readLine()
  Person.signatureReduction(inputName)
}

object Person {

  def apply(input: String): List[String] = input.split("(\\W+)").toList

  def unapply(personName: List[String]): Option[String] = {
    if(personName.length == 2){
      Some(personName.head.substring(0,1).capitalize+". "+personName(1).substring(0,1).capitalize+".")
    }  else None
  }

  def signatureReduction(input: String) : Unit = {
    val signature = Person(input)
    if(signature != null) {
      signature match {
        case Person(name) => println(name)
        case _ => println("Oops, something is wrong")
      }
    }
    else println("Oops, something is wrong")
  }
}
