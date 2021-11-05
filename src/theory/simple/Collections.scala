package theory.simple

object Collections extends App {

  // List
  val oneTwoList = List("one", "two")
  val freeFourList = List("tree", "four")
  // ::: - метод объединения списков
  val oneTwoThreeFour = oneTwoList ::: freeFourList
  println(oneTwoThreeFour) //

  // Set
  val emptySet: Set[Int] = Set()
  val numberSet = Set(1, 2, 3, 4, 5)
  val stringSet = Set("one", "two", "three", "four", "five")
  val test = numberSet.+(6)

  println(numberSet.head) // 5
  println(numberSet.tail) // HashSet(1, 2, 3, 4)
  println(test) // HashSet(5, 1, 6, 2, 3, 4)

  println(stringSet.head) // two
  println(stringSet.tail) // HashSet(five, one, four, three)

  // Seq
  val sequence = Seq(10, 20, 30, 40)
  println(sequence) // List(10, 20, 30, 40)
  val moreSequence = sequence ++ Seq(50, 60)
  println(moreSequence) // List(10, 20, 30, 40, 50, 60)
  println(moreSequence(3)) // 40
  println(moreSequence.search(60)) // Found(5)

  // Map
  val emptyMap: Map[Int, String] = Map()
  val newMap: Map[Int, String] = Map((1, "one"), (2, "two"), (3, "three"))
  // или с синтаксическим сахаром
  val newMap2: Map[Int, String] = Map(1 -> "thirst", 2 -> "second", 3 -> "third") //


  println(newMap) // Map(1 -> one, 2 -> two, 3 -> three)
  println(newMap2) // Map(1 -> thirst, 2 -> second, 3 -> third)

  val newMap3: Map[Int, String] = newMap + (4 -> "four") // Map(1 -> one, 2 -> two, 3 -> three, 4 -> four)
  println(newMap3)

  ////////////////// Array //////////////////////////////////////////////
  val stringArray: Array[String] = Array("h", "e", "l", "l", "o", ".")
  // stringArray.update(5, "!")
  // или
  stringArray(5) = "!"
  println(stringArray.toList) // List(h, e, l, l, o, !)
  println(stringArray.mkString("-")) // h-e-l-l-o-!

  // создание пустого массива заданного размера
  val twoElements: Array[Int] = Array.ofDim[Int](2)
  twoElements.foreach(println) // 00

  // преобразование типа
  val seqNumber: Seq[String] = stringArray
  println(seqNumber) // ArraySeq(h, e, l, l, o, !)

  ////////////////// Tuple //////////////////////////////////////////////

  // Tuple содержит элементы разных типов

  val aTuple: (Int, String) = (100, "this a tuple")
  val sameTuple: (Int, Double, String) = Tuple3(10, 11.5, "str")
  val manyTuple: (Int, Double, Float, String, Char, Boolean) = Tuple6(1, 2.0, 3.1f, "4", '5', true)
  println(manyTuple) // (1,2.0,3.1,4,5,true)
  println(manyTuple._2) // 2.0

  // замена элементов при копировании
  val copy: (Int, String) = aTuple.copy(_1 = 33)
  println(copy) // (33,this a tuple)
  println(aTuple.swap) // замена местами (только для 2 элементов) (this a tuple,100)

  ////////////////// Range //////////////////////////////////////////////
  val range: Seq[Int] = 1 until 5
  range.foreach(print) // 1234
  println

  // использование в качестве рекурсивного вывода
  (1 to 3).foreach(_ => print("something")) // somethingsomethingsomething
  println

  // создание коллекции
  val rangeVector: Vector[Int] = (1 to 4).toVector
  println(rangeVector) // Vector(1, 2, 3, 4)

  // map VS flatmap
  // map:  заданная функция применяется к каждому элементу списка.
  val fruits = List("apple", "banana")
  val upperFruits = fruits.map(_.toUpperCase)
  println(upperFruits) // List(APPLE, BANANA)

  // flatMap преобразует каждый элемент в целый список элементов и выполняет действия уже с ними
  // , а потом результат собирает в одно целое.
  val flatUpper = fruits.flatMap(_.toUpperCase)
  println(flatUpper) // List(A, P, P, L, E, B, A, N, A, N, A)

  val point = "watermelon".flatMap(fruit => fruit + ".")
  println(point) // w.a.t.e.r.m.e.l.o.n.

  // комбинация map b flatmap
  val list1 = List(1, 2)
  val list2 = List("a", "b")
  val combine = list1.flatMap(number => list2.map(str => str + number))
  println(combine) // List(a1, b1, a2, b2)

  // аналогичный код с for
  val forCombine = for {
    number <- list1
    str <- list2
  } yield str + number
  println(forCombine) // List(a1, b1, a2, b2)

  // filter
  val combineFilter = list1.filter(_ > 1).flatMap(number => list2.map(str => str + number))
  println(combineFilter) // List(a2, b2)

  // аналогичный код с for
  val forCombineFilter = for {
    number <- list1 if number > 1
    str <- list2
  } yield str + number
  println(forCombineFilter) // // List(a2, b2)

  println(Seq(3, 2, 1, 3, 3).search(3))

  // примеры комбинирования
  val list = List(1, 2, 3)
  val doubler = (x: Int) => List(x, x * 2)
  println(list.map(doubler)) // List(List(1, 2), List(2, 4), List(3, 6))
  println(list.flatMap(doubler)) // List(1, 2, 2, 4, 3, 6)
  println(doubler(5)) // List(5, 10)

}
