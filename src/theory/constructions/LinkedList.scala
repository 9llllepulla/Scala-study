package theory.constructions

import scala.annotation.tailrec


/**
 * Связный список
 */
object LinkedList extends App {

  val linkedList = new SimpleLog("a", new SimpleLog("b", new SimpleLog("c", SimpleEmpty)))
  println(linkedList.last)
  println(linkedList.all)
  println(linkedList.previous.previous.last)
}

abstract class SimpleLogList {

  def add(message: String): SimpleLogList
  def previous: SimpleLogList
  def isEmpty: Boolean
  def last: String
  def all: String
}
class SimpleLog(val message: String, val previousLog: SimpleLogList) extends SimpleLogList {

  override def add(message: String): SimpleLogList = new SimpleLog(message, this)

  override def previous: SimpleLogList = {
    if(previousLog != SimpleEmpty) previousLog
    else SimpleEmpty
  }

  override def isEmpty: Boolean = previousLog==SimpleEmpty

  override def last: String = message

  override def all: String = {
    @tailrec
    def loop(linkedList: SimpleLogList, acc: String = message): String = {
      if(linkedList == SimpleEmpty) acc
      else loop(linkedList.previous, s"$acc ${linkedList.last}")
    }
    loop(previousLog)
  }
}

object SimpleEmpty extends SimpleLogList {

  override def add(message: String): SimpleLogList = new SimpleLog(message, SimpleEmpty)

  override def previous: SimpleLogList = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def last: String = throw new NoSuchElementException

  override def all: String = ""
}
