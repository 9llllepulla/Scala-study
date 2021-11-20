package exercises

import scala.math.BigDecimal.RoundingMode.HALF_UP

object Potato extends App {

  /**
   * Дано:
   *
   * weight: BigDecimal  - вес имеющегося картофеля.
   * potatoWaterRatio: Double  - (0,1), доля воды в картофеле до того, как из него сделали чипсы.
   * crispsWaterRatio: Double - (0,1), доля воды в чипсах.
   *
   * Найдите массу чипсов, которая получится.
   */
  def crispsWeight(weight: BigDecimal, potatoWaterRatio: Double, crispsWaterRatio: Double): BigDecimal = {
    ((weight - (weight * potatoWaterRatio)) / (1- crispsWaterRatio)).setScale(5, HALF_UP)
  }

  println(crispsWeight(90.0, 0.9, 0.1))

  /**
   * Посчитайте число единиц в битовой записи числа, считанного с клавиатуры, и выведите на экран.
   */
  val num = scala.io.StdIn.readInt()
  val bitCount = BigInt(num).toString(2).count(ch => ch == '1')
  println(bitCount)

}
