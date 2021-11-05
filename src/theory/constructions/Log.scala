package theory.constructions

import scala.annotation.tailrec

class Log[+T](val message: T, val previousLog: LogList[T]) extends LogList[T] {

  override def add[U >: T](message: U): LogList[U] = new Log(message, this)

  override def previous: LogList[T] = {
    if (previousLog != Empty) previousLog
    else Empty
  }

  override def isEmpty: Boolean = previousLog == Empty

  override def last[U >: T]: U = message

  override def all: String = {
    @tailrec
    def loop(linkedList: LogList[T], acc: String = message.toString): String = {
      if (linkedList == Empty) acc
      else loop(linkedList.previous, s"$acc ${linkedList.last}")
    }

    loop(previousLog)
  }
}
