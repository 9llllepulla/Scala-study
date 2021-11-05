package theory.collections

object ListCollection extends App {

  // List
  val oneTwoList = List("one", "two")
  val freeFourList = List("tree", "four")
  // ::: - метод объединения списков
  val oneTwoThreeFour = oneTwoList ::: freeFourList
  println(oneTwoThreeFour) // List(one, two, tree, four)
  // оператор cons :: - добавляет в начало списка новый элемент и возвращает полученный список
  val zeroOneTwoThreeFour = "zero" :: oneTwoThreeFour
  println(zeroOneTwoThreeFour) // List(zero, one, two, tree, four)

  // способ инициализации нового списка:
  val numbersList = 1 :: 2 :: 3 :: 4 :: Nil
  println(numbersList) // List(1, 2, 3, 4)

  // пустой список
  val emptyList = List()
  val emptyList2 = Nil
  println(emptyList) // List()
  println(emptyList2) // List()

}
