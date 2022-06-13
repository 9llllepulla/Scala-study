package theory.collections

import scala.collection.mutable

/**
 * Отображения (ассоциативные массивы)
 */
object MapCollections extends App {

  // Map
  // изменяемая Map
  val treasureMap = mutable.Map[Int, String]()
  treasureMap += (1 -> "Go to island")
  treasureMap += (2 -> "Find big X on ground")
  treasureMap += (3 -> "Dig.")
  println(treasureMap(2)) // Find big X on ground

  // неизменяемая Map
  val romanNum = Map(1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV")
  println(romanNum(4)) // IV

  val emptyMap: Map[Int, String] = Map()
  val newMap: Map[Int, String] = Map((1, "one"), (2, "two"), (3, "three"))
  // или с синтаксическим сахаром
  val newMap2: Map[Int, String] = Map(1 -> "thirst", 2 -> "second", 3 -> "third") //


  println(newMap) // Map(1 -> one, 2 -> two, 3 -> three)
  println(newMap2) // Map(1 -> thirst, 2 -> second, 3 -> third)

  val newMap3: Map[Int, String] = newMap + (4 -> "four") // Map(1 -> one, 2 -> two, 3 -> three, 4 -> four)
  println(newMap3)

}
