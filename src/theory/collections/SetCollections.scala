package theory.collections

import scala.collection.mutable

/**
 * Множества
 */
object SetCollections extends App {

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

  // Неизменяемые множества
  var jetSet = Set("Boeing", "AirBus")
  println(jetSet) // Set(Boeing, AirBus)
  jetSet += "Lear" // переменной jetSet присваивается новое создаваемое множество, поэтому переменная var
  println(jetSet) // Set(Boeing, AirBus, Lear)

  // Изменяемые множества
  val movieSet = mutable.Set("Hitch", "Poltergeist")
  println(movieSet) // HashSet(Hitch, Poltergeist)
  movieSet += "Shrek" // т.к. множество изменяемое, то переменная val, а изменяется множество
  println(movieSet) // HashSet(Hitch, Shrek, Poltergeist)

  // поиск в тексте уникальных слов
  val text = "See sport run. Run, Spot. Run for sport spot!"
  val words = text.split("[ !,.]+").map(word => word.toLowerCase).toSet
  println(words) // HashSet(for, run, sport, spot, see) - более 5 элементов HashSet(иначе - Set)



}
