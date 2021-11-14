package theory.simple

import java.io.File
import scala.io.StdIn.readInt

/**
 * Выражения
 */
object Expression extends App {

  // if возвращает значения
  val fileName =
    if(!args.isEmpty) args(0)
    else "default.txt"

  /**
   * выражения for
   */
  // получение списка файлов в каталоге
  val filesHere = (new File(".")).listFiles
  for (file <- filesHere) println(file)

  // применение типа Range
  for(index <- 1 to 4) println("Four iteration " + index)
  // если не нужно включать верхнюю границу диапазона
  for(index <- 1 until 4) println("Three iteration "+ index)

  // фильтрация - можно добавить фильтр в условие
  for(file <- filesHere if file.getName.endsWith(".scala")) println(file)
  // альтернативный вариант:
  for(file <- filesHere)
    if (file.getName.endsWith(".scala"))
      println(file)

  // использование нескольких фильтров
  for(
    file <- filesHere
    if file.isFile
    if file.getName.endsWith(".scala")
  ) println(file)

  // использование в качестве рекурсивного вывода
  (1 to 3).foreach(_ => print("something")) // somethingsomethingsomething



  ////////////////////////////////////////////////////////////////////////////////////////////
  var res = 1
  for(_ <- 1 to 3) {
    val x = readInt()
    res *= x
  }
  println(res)
  print((1 to 3).map(_ => readInt()).product) // вариант решения вместо цикла
  ///////////////////////////////////////////////////////////////////////////////////////////

}
