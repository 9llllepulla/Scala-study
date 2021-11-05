package theory.simple

import scala.util.{Failure, Success, Try}

object Exception extends App {

  // Try - аналог try-catch-finally

  def unsafeMethod(): String = throw new RuntimeException("Sorry, not your day")

  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure) // Failure(java.lang.RuntimeException: Sorry, not your day)

  // альтернативный способо
  val anotherPotentialFailure = Try {
    // код, содержащий исключения
  }

  // возможность заранее проверить содержит ли метод исключение
  println(potentialFailure.isSuccess) // false

  // Try & orElse
  def unsafeMethod2(): String = throw new RuntimeException("Sorry, not your day")

  def myMethod(): String = "My method is valid"

  val executeWithTry = Try(unsafeMethod2()).orElse(Try(myMethod()))
  println(executeWithTry) // Success(My method is valid)

  // Еще одним вариантом будет обернуть в Try результат работы вашего метода.
  def methodWhichFails(): Try[String] = Failure(new RuntimeException("Ooops..."))

  def methodWhichSucceeds(): Try[String] = Success("Everything is OK")

  val tryMethods = methodWhichFails() orElse methodWhichSucceeds()
  println(tryMethods) // Success(Everything is OK)

  // Success -> Failure
  val someVal = Success(3)
  println(someVal.filter(_ > 5)) // Failure(java.util.NoSuchElementException: Predicate does not hold for 3)

}

