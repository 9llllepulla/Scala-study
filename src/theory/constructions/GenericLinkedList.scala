package theory.constructions

/**
 * https://stepik.org/lesson/463107/step/4?unit=453732
 * https://stepik.org/lesson/463105/step/7?unit=453730
 * Связный список на дженериках
 */
object GenericLinkedList extends App {

  val linkedList = new Log("a", new Log(1, new Log(2.5, Empty)))
  println(linkedList.last)
  println(linkedList.all)
  println(linkedList.previous.previous.last)

}
abstract class LogList[+T] {

  def add[U >: T](message: U): LogList[U]
  def previous: LogList[T]
  def isEmpty: Boolean
  def last[U >: T]: U
  def all: String
}

object Empty extends LogList[Nothing] {

  override def add[U >: Nothing](message: U): LogList[U] = new Log(message, Empty)

  override def previous: LogList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def last[U >: Nothing]: Nothing = throw new NoSuchElementException

  override def all: String = ""
}
