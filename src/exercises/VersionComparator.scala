package exercises

import scala.annotation.tailrec

object VersionComparator extends App {

  /**
   * Версия программы может задаваться в разных форматах, но мы поработаем со случаем, когда версия задается
   * последовательностью чисел, указывающих на масштаб вносимых в программу изменений.
   *
   * Например, для указания предварительных и неофициальных версий обычно указываются числа меньше 1, такие как 0.9.
   * Первой версии программы присваивается номер 1.0, версия с небольшими изменениями может иметь номер 1.11,
   * и только когда создается программа с новой функциональностью — версия увеличивается на 1 и становится 2.0.
   *
   * Ваша задача - написать метод, позволяющий сравнивать версии между собой: def compare(v1: String, v2: String): Int. Метод возвращает:
   *        -1 (если v1 < v2)
   *        0 (если v1 = v2)
   *        1 (если v1> v2)
   *
   * Приведем несколько примеров:
   *        результатом сравнения 1.0.2.03 и 1.1.0 будет -1 (1.0.2.03 < 1.1.0, так как 0 < 1)
   *        результатом сравнения 2.1 и 2.01 будет 0 (не забудьте предусмотреть в программе случаи, когда номера начинаются с нулей)
   *        результатом сравнения 3.0 и 3.0.0.0 также будет 0
   */
  def compare(version1: String, version2: String): Int = {
    if (version1.length < version2.length) {
      comparator(zeroExtender(splitter(version1), version2.length - version1.length), splitter(version2))
    } else {
      comparator(splitter(version1), zeroExtender(splitter(version2), version1.length - version2.length))
    }
  }

  def comparator(v1List: List[Int], v2List: List[Int]): Int = {

    val equal: PartialFunction[(Int, Int), Int] = {
      case Tuple2(a, b) if a == b => 0
    }

    val more: PartialFunction[(Int, Int), Int] = equal.orElse[(Int, Int), Int] {
      case Tuple2(a, b) if a > b => 1
      case Tuple2(a, b) if a < b => -1
    }

    @tailrec
    def loop(count: Int): Int ={
      if(count == v1List.length) 0
      else if(more((v1List(count), v2List(count))) !=0) more((v1List(count), v2List(count)))
      else loop(count+1)
    }
    loop(0)
  }

  def splitter(str: String): List[Int] = {
    str.split("\\W+").map(num => num.toInt).toList
  }

  def zeroExtender(numSet: List[Int], targetLength: Int): List[Int] = {
    def loop(count: Int, acc: List[Int] = numSet): List[Int] = {
      if(count >= targetLength) acc
      else loop(count+1, acc ::: List(0))
    }
    loop(numSet.size)
  }

  println(compare("1.0.2", "1.1.0")) // -1
  println(compare("2.001", "2.01")) // 0
  println(compare("1.0.2.03", "1.1.0"))  // -1
  println(compare("3.0", "3.0.0.0"))  // 0
  println(compare("0.9", "1.0")) // -1
  println(compare("1.9", "1.0"))  // 1
  println(compare("3.0", "3.0.0.1"))  // -1

}
