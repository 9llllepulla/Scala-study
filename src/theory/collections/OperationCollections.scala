package theory.collections

object OperationCollections extends App {

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
