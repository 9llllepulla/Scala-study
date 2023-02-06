package exercises.conecenter

import exercises.conecenter.MessageDecoder.{isCode, lines}

import java.util.function.Predicate

case class MessageDecoder(firstLines: List[String], secondLines: List[String]) {

  val message: String = lines(firstLines, secondLines, isCode.negate()).foldLeft("")(_ + _)

  val code: List[String] = lines(firstLines, secondLines, isCode)

}

object MessageDecoder {

  def decodeMessage(firstFileLines: List[String], secondFileLines: List[String]): List[String] = {
    val decoder = new MessageDecoder(firstFileLines, secondFileLines)
    KuuseTree.messageTree(decoder.message, decoder.code).asList
  }

  private def lines(firstFileLines: List[String], secondFileLines: List[String], pr: Predicate[List[String]]): List[String] = {
    if (pr.test(firstFileLines)) firstFileLines
    else if (pr.test(secondFileLines)) secondFileLines
    else List()
  }

  private val isCode: Predicate[List[String]] = lines => lines.nonEmpty && lines.exists(line => line.startsWith("-"))

}
