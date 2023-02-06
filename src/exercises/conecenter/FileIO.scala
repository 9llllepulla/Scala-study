package exercises.conecenter

import java.io.File
import java.nio.file.Files
import scala.jdk.CollectionConverters.CollectionHasAsScala

object FileIO {

  def save(decodeMessageAsLines: List[String], fileName: String): Unit = ???

  def readAsLines(file: File): List[String] = {
    if (file.exists() && file.getName.endsWith(".in")) {
      Files.readAllLines(file.toPath).asScala.toList
    }
    else List()
  }

}
