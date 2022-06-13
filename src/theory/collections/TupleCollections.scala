package theory.collections

/**
 * Кортежи
 */
object TupleCollections extends App {

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

  /**
   * хорошо подходят для возвращения из методов нескольких значений
   */
  def longestWord(words: Array[String]): (String, Int) = {
    var word = words(0)
    var index = 0
    for (i <- 1 until words.length) {
      if (words(i).length > words.length) {
        word = words(i)
        index = i
      }
    }
    (word, index)
  }
  val tupleLongestWord = longestWord("The quick brown fox".split(" "))
  println(tupleLongestWord) // (brown,2)

}
