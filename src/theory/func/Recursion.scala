package theory.func

import scala.annotation.tailrec

object Recursion extends App {

  /**
   * Хвостовая рекурсия
   */

  // Факториал с помощью ХВОСТОВОЙ рекурсии
  def factorialTailRecursion(n: Int): Int = {
    @tailrec
    def loop(x: Int, accumulator: Int = 1): Int = {
      if (x == 1) accumulator
      else loop(x - 1, x * accumulator)
    }

    loop(n)
  }
  println(factorialTailRecursion(4))

  // вывод слова n-Раз с помощью рекурсии
  def repeatWord(word: String, n: Int): String = {
    @tailrec
    def loop(index: Int, acc: String = word): String = {
      if (index == 1) acc
      else loop(index - 1, s"$word $acc")
    }

    loop(n)
  }
  println(repeatWord("пук", 5))

  // вычисление степеней 2
  def powerOfTwo(pow: Int): BigInt = {
    @tailrec
    def loop(index: Int, acc: BigInt = 2): BigInt = {
      if (index == 0) 1
      else if (index == 1) acc
      else loop(index - 1, acc * 2)
    }

    loop(pow)
  }
  println(powerOfTwo(3))

  def powerOfTwo1(power: Int): BigInt = {
    @tailrec
    def loop(i: Int, acc: BigInt = 1): BigInt = {
      if (i < 0) throw new ArithmeticException
      else if (i < 1) acc
      else loop(i - 1, acc * 2)
    }

    loop(power)
  }
  println("test: " + powerOfTwo1(0))

}
