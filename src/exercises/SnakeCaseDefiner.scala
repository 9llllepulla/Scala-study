package exercises

import scala.io.StdIn.readLine

object SnakeCaseDefiner extends App {

  /**
   * Ваша задача - определить, является ли переданная строка input записанной в snake-case стиле.
   * Требования snake-case к строке:
   * Должна содержать только строчные латинские буквы и символ подчёркивания
   * Символ подчёркивания не должен стоять в начале и в конце строки
   * Два символа подчёркивания не могут стоять рядом
   * Считайте из стандартного потока ввода строку и напечатайте true,
   * если она удовлетворяет требованиям выше, false иначе.
   */

  val lettersSnakeCase = "^[A-Za-z](_[A-Za-z]_)*$" // s_n_a_k_e
  val wordsSnakeCase = "^[a-z]+(_[a-z]+)*$" // snake_case

  def defineSnakeCase(input: String): Boolean = input.matches("^[a-z]+(_[a-z]+)*$")
  println(defineSnakeCase(readLine()))

}
