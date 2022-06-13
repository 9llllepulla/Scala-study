package theory.collections

import scala.collection.mutable.ListBuffer

/**
 * Списки являются неизменяемой структурой
 *
 * */
object ListCollection extends App {

  /** создание List */
  val oneTwoList = List("one", "two")
  val freeFourList = List("tree", "four")

  /** ::: - метод объединения списков (конкатенация) */
  val oneTwoThreeFour = oneTwoList ::: freeFourList
  println(oneTwoThreeFour) // List(one, two, tree, four)
  val oneFive = List(1, 2) ::: List(3, 4, 5)
  println(oneFive) // List(1, 2, 3, 4, 5)
  val threeList = List(1, 2, 3) ::: List(4, 5, 6) ::: List(7, 8)
  println(threeList) // List(1, 2, 3, 4, 5, 6, 7, 8)

  /** оператор cons :: - добавляет в начало списка новый элемент и возвращает полученный список */
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

  /** Операции над списками */
  val fruits = "apple" :: "orange" :: "banana" :: Nil
  println(fruits.isEmpty) // false
  println(fruits.head) // apple
  println(fruits.tail) // List(orange, banana)
  println(fruits.reverse) // List(banana, orange, apple)
  println(fruits.init) // List(apple, orange)
  println(fruits.last) // banana
  println(fruits.indices) // Range 0 until 3

  /** Алгоритм сортировки вставками */
  def sort(xs: List[Int]): List[Int] = {
    // внутренний метод
    def insert(x: Int, xs: List[Int]): List[Int] = {
      if (xs.isEmpty || x <= xs.head) x :: xs
      else xs.head :: insert(x, xs.tail)
    }

    if (xs.isEmpty) Nil
    else insert(xs.head, sort(xs.tail))
  }

  val unSort = List(1, 5, 4, 9, 3, 2, 7)
  val sorted = sort(unSort)
  println(sorted) // List(1, 2, 3, 4, 5, 7, 9)

  /** Разбор списка с помощью паттерна сопоставления с образцом */
  val List(a, b, c) = fruits
  println(a) // apple
  println(b) // orange
  println(c) // banana

  /** Сопоставление с образцом, если длина списка не известна */
  val d :: e :: rest = fruits
  println(d) // apple
  println(e) // orange
  println(rest) // List(banana)

  /** Сортировка вставками с помощью сопоставления с образцом */
  def insertSort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case x :: xs1 => insertS(x, insertSort(xs1))
  }

  def insertS(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => {
      if (x <= y) x :: xs
      else y :: insertS(x, ys)
    }
  }

  val unsorted = List(11, 33, 88, 93, 2, 17, 34, 76, 90, 14, 8, 1)
  val sortedList = insertSort(unsorted)
  println(sortedList) // List(1, 2, 8, 11, 14, 17, 33, 34, 76, 88, 90, 93)

  /** Map, flatmap, foreach */
  println(List(1, 2, 3) map (_ + 1)) // List(2, 3, 4)
  val words = List("the", "quick", "brown", "fox")
  println(words map (_.toList)) // List(List(t, h, e), List(q, u, i, c, k), List(b, r, o, w, n), List(f, o, x))
  println(words flatMap (_.toList)) // List(t, h, e, q, u, i, c, k, b, r, o, w, n, f, o, x)
  var sum = 0
  List(1, 2, 3, 4, 5) foreach (sum += _)
  println(sum) // 15

  /** filter, partition, find, takeWhile, dropWhile, span */
  println(List(1, 2, 3, 4, 5) filter (_ % 2 == 0)) // List(2, 4)
  println(List(1, 2, 3, 4, 5) partition (_ % 2 != 0)) // (List(1, 3, 5),List(2, 4))
  println(List(1, 2, 3, 4, 5) find (_ % 2 == 0)) // Some(2) - первый найденный элемент
  println(List(1, 2, 3, -4, 5) takeWhile (_ > 0)) // List(1, 2, 3)
  println(words dropWhile (_ startsWith "t")) // List(quick, brown, fox)
  println(List(1, 2, 3, -4, 5) span (_ > 0)) // (List(1, 2, 3),List(-4, 5)) - возвращает пару из 2 списков

  /**
   * fold - свертка, объединяет элементы списка с помощью оператора (op):
   *
   * List(a, b, c).foldLeft(z)(op) => op(op(op(z, a), b), c)
   * List(a, b, c).foldRight(z)(op) => op(a, op(b, op(c, z)))
   *
   * */
  println(List(1, 2, 3, 4, 5).foldLeft("a")(_ + "b" + _)) // левая свертка - ab1b2b3b4b5
  println(List(1, 2, 3, 4, 5).foldRight("a")(_ + "b" + _)) // правая свертка - 1b2b3b4b5ba

  /** sort */
  println(List(4, 7, 1, 2, 5) sortWith (_ < _)) // List(1, 2, 4, 5, 7)
  println(fruits sortWith(_.length > _.length)) // List(orange, banana, apple)

  /** range - создание списка в диапазоне */
  println(List.range(1, 8)) // List(1, 2, 3, 4, 5, 6, 7)
  println(List.range(1, 12, 3)) // List(1, 4, 7, 10)
  println(List.range(12, 1, -3)) // List(12, 9, 6, 3)

  /** fill - заполнение списка элементами */
  println(List.fill(5)('s')) // List(s, s, s, s, s)
  println(List.fill(5)("hello")) // List(hello, hello, hello, hello, hello)
  println(List.fill(3, 2)("j")) // List(List(j, j), List(j, j), List(j, j)) - многомерные списки

  /** tabulate - заполнение списка вычисляемыми элементами */
  println(List.tabulate(7)(n => n + n)) // List(0, 2, 4, 6, 8, 10, 12)

  /** конкатенация списков */
  println(List.concat(List('a', 'b'), List('c', 'd'))) // List(a, b, c, d)

  /** zip, lazyZip - создание списка пар из двух списков (лишний элемент отбрасывается) */
  println((List(10, 20) zip List(3, 4, 5)).map {
    case (x, y) => x * y
  }) // List(30, 80)

  println((List(10, 20) lazyZip  List(3, 4, 5)).map {
    case (x, y) => x + y
  }) // List(13, 24)

  /**
   * ListBuffer - изменяемый объект помогает эффективно строить списки, когда в них нужно добавлять элементы в конец списка
   */
  val buffer = new ListBuffer[Int]
  buffer += 1
  buffer += 2
  println(buffer) // добавление в конец ListBuffer(1, 2)
  3 +=: buffer
  println(buffer) // добавление в начало ListBuffer(3, 1, 2)
  println(buffer.toList) // конвертация в список List(3, 1, 2)



}
