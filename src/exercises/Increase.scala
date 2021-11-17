package exercises

import scala.annotation.tailrec

object Increase extends App {

  /**
   * Требуется написать программу, которая:
   * увеличивает заданное число x на число y n-ое количество раз
   * выводит на экран полученное на шаге 1 число столько раз, сколько в нем цифр, и фразу is the result
   */
  def increase(x: Int, y: Int, n: Int): Int = {
    @tailrec
    def loop(count: Int, acc: Int = x): Int = {
      if (count == 0) acc
      else loop(count - 1, acc + y)
    }

    loop(n)
  }

  def createIncreaseResultString(num: Int): String = {
    @tailrec
    def loop(count: Int, acc: String = s"$num"): String = {
      if (count == 1) acc
      else loop(count - 1, s"$num $acc")
    }

    loop(num.toString.length)
  }
  //println(createIncreaseResultString(increase(fArgs(0), fArgs(1), fArgs(2)))++" is the result")

}
