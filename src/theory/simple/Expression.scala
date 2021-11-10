package theory.simple

import java.io.File

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








}
