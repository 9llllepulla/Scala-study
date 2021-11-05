package theory.simple

object Options extends App {

  // Опции - это всего лишь способ защиты от NullPointerException
  def unsafeMethod: String = null;

  def initMethod: String = "init"

  println(Option(unsafeMethod))
  println(Option(initMethod))
  Some(initMethod)

  val someVal = Some(2)
  println(someVal.map(_ * 2))
  println(someVal.get)

  println(someVal.filter(x => x > 10)) // None
  println(someVal.flatMap(x => Option(x * 2))) // Some(4)
  println(someVal.filter(x => x > 0)) // Some(2)

}
