package theory.caseclasses

object OptionalClass extends App {

  // Option - способ защиты от NullPointerException
  def unsafeMethod: String = null;

  def initMethod: String = "init"

  println(Option(unsafeMethod)) // None
  println(Option(initMethod)) // Some(init)
  println(Some(initMethod).get) // init

  val someVal = Some(2)
  println(someVal.map(_ * 2)) // Some(4)
  println(someVal.get) // 2

  println(someVal.filter(x => x > 10)) // None
  println(someVal.flatMap(x => Option(x * 2))) // Some(4)
  println(someVal.filter(x => x > 0)) // Some(2)

  val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo", "Russia" -> "Moscow")

  val capitalOfRussia = capitals get "Russia" // Option[String]
  println(capitalOfRussia) // Some(Moscow)

  val capitalOfNorthPole = capitals get "North Pole" // Option[String]
  println(capitalOfNorthPole) // None


}
