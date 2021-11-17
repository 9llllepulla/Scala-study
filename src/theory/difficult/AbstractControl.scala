package theory.difficult

import java.io.File

object AbstractControl extends App {

  /**
   * Управляющие абстракции
   */
  private def filesHere: Array[File] = new File(".").listFiles

  def filesMatching(matcher: String => Boolean): Array[File] = {
    for(file <- filesHere; if matcher(file.getName))
      yield file
  }

  // три поисковых метода, пользующиеся вспомогательным методом
  def filesEnding(query: String): Array[File] = filesMatching(_.endsWith(query)) // отбор по окончанию файла

  def filesContaining(query: String): Array[File] = filesMatching(_.contains(query)) // отбор по содержанию в имени файла

  def filesRegex(query: String): Array[File] = filesMatching(_.matches(query)) // поиск по регулярке

}
