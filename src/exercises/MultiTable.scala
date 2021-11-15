package exercises

/**
 * Вывод таблицы умножения
 */
object MultiTable extends App {

  // возвращение строки в виде последовательности
  def makeRowSeq(row: Int): Seq[String] = {
    for(col <- 1 to 10) yield {
      val prod = (row * col).toString
      val padding = " " * (4 - prod.length)
      padding + prod
    }
  }

  // возвращение таблицы в виде строковых значений, по одному значению на каждую строчку
  def table(): String = {
    val tableSeq =
      for(row <- 1 to 10)
        yield makeRowSeq(row).mkString

    tableSeq.mkString("\n")
  }

  println(table())
}
