package theory.constructions

import scala.collection.mutable.ArrayBuffer

/** Очередь целых чисел */
abstract class IntQueue {

  def get(): Int

  def put(x: Int): Unit
}

/** Базовая реализация очереди целых чисел */
class BasicIntQueue extends IntQueue {

  // Буфер в виде массива
  private val buf = new ArrayBuffer[Int]

  def get(): Int = buf.remove(0)

  def put(x: Int): Unit = {
    buf += x
  }
}

/** Наращиваемая модификация удваивания числа */
trait Doubling extends IntQueue {
  /* abstract override - в данном случае означает, что трейт может быть примешан в класс, имеющий
     конкретную реализацию данного метода */
  abstract override def put(x: Int): Unit = {
    super.put(2 * x)
  }
}

/** Наращиваемая модификация инкремента числа */
trait Incrementing extends IntQueue {
  abstract override def put(x: Int): Unit = {
    super.put(x + 1)
  }
}

/** Наращиваемая модификация фильтрации целых чисел */
trait PositiveFiltering extends IntQueue {
  abstract override def put(x: Int): Unit = {
    if (x >= 0) super.put(x)
  }
}

/** примешивание трейта к базовому классу */
class DoublingQueue extends BasicIntQueue with Doubling

/** Наращиваемые модификации */
object StackableModifications extends App {

  val doublingQ = new DoublingQueue()
  doublingQ.put(10)
  println(doublingQ.get()) // 20

  /* Также можно примешивать трейт во время создания объекта */
  val dQu = new BasicIntQueue with Doubling
  dQu.put(15)
  println(dQu.get()) // 30

  /* Трейт находящийся правее вступает в силу первым */
  val stackable = new BasicIntQueue with Incrementing with PositiveFiltering
  stackable.put(-1)
  stackable.put(0)
  stackable.put(1)
  println(stackable.get()) // 1
  println(stackable.get()) // 2

}
