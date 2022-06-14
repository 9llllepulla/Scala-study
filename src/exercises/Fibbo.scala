package exercises

import scala.annotation.tailrec

object Fibbo extends App {

  def fibs(num: Int): Int = {
    @tailrec
    def loop(idx: Int, pre: Int = 1, post: Int = 2): Int = {
      if(num == 0) 0
      else if (num == 1) 1
      else if (num == 2) 1
      else {
        if(idx == 3) post
        else loop(idx-1, post, pre + post)
      }
    }
    loop(num)
  }

  // Не мое решение
  @tailrec
  def fibs2(num: Int, prev: Int = 1, acc: Int = 1): Int = num match {
    case 0 => 0
    case 1 | 2 => acc
    case _ => fibs2(num - 1, acc, acc + prev)
  }

  def fibs3(num: Int) = (1 to num).foldLeft((0, 1))((acc, _) => (acc._2 , acc._1 + acc._2))._1

  println(fibs2(2)) //
}
