package theory.collections

object TupleCollections extends App {

  ////////////////// Tuple //////////////////////////////////////////////

  // Tuple содержит элементы разных типов

  val pair = (99, "ninety-nine")
  println(pair) // (99,ninety-nine)
  println(pair._1) // 99
  println(pair._2) // ninety-nine

  val aTuple: (Int, String) = (100, "this a tuple")
  val sameTuple: (Int, Double, String) = Tuple3(10, 11.5, "str")
  val manyTuple: (Int, Double, Float, String, Char, Boolean) = Tuple6(1, 2.0, 3.1f, "4", '5', true)
  println(manyTuple) // (1, 2.0, 3.1, 4, 5, true)
  println(manyTuple._2) // 2.0

  // замена элементов при копировании
  val copy: (Int, String) = aTuple.copy(_1 = 33)
  println(copy) // (33,this a tuple)
  println(aTuple.swap) // замена местами (только для 2 элементов) (this a tuple,100)

}
