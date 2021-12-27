package exercises

/**
 * Реализация параметризованной функции сортировки слиянием
 */
object MergeSort extends App {

  def mSort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = {

    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
      case (Nil, _) => ys
      case (_, Nil) => xs
      case (x :: xs1, y :: ys1) =>
        if (less(x, y)) x :: merge(xs1, ys)
        else y :: merge(xs, ys1)
    }

    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt(n)
      merge(mSort(less)(ys), mSort(less)(zs))
    }
  }

  val unSort = List(2, 7, 9, 4, 0, 22, 6, 77, 90)
  val sort = mSort((x: Int, y: Int) => x < y)(unSort)
  println(sort) // List(0, 2, 4, 6, 7, 9, 22, 77, 90)

  val unSortAlphaBet = List('d', 's', 'r', 'q')
  val sortAlphaBet = mSort((x: Char, y: Char) => x > y)(unSortAlphaBet)
  println(sortAlphaBet) // List(s, r, q, d)

  /** Частично примененная функция */
  val str = List("w", "rw", "kk", "ler")
  val stringSortFunc = mSort((x: String, y: String) => x < y) _
  println(stringSortFunc(str)) // List(kk, ler, rw, w)
}
