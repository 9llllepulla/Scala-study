package exercises

import scala.annotation.tailrec

object LogSystem extends App {

  val logInfo = new Logger()
  //logInfo.print
  //val log = logInfo.info()
  //log.print
  //println(log.count)
  new Logger().info(3).print()
}

class Logger(msgNum: Int = 0){

  var count: Int = msgNum

  def info(): Logger = {
    count = msgNum + 1
    println(s"INFO New Message $count")
    new Logger(count)
  }

  def info(msgCount: Int): Logger = {
    count = msgCount
    @tailrec
    def loop(index: Int, logger: Logger = new Logger()): Logger ={
      if(index <= 0) this
      else loop(index-1, logger.info())
    }
    loop(msgCount)
  }

  def print(): Unit = println(count)

  /* //// преподавательский вариант  ///////////
  def info(): Logger = {
    println("INFO new message "+msgNum)
    new Logger(msgNum + 1)
  }

  def info(n: Int): Logger = {
    if (n <= 0) this
    else info().info(n - 1)
  }
   *//////////////////////////////////////////////
}
