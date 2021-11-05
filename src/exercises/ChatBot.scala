package exercises

import scala.annotation.tailrec

object ChatBot extends App {

  /**
  Напоследок - давайте создадим простенького чат-бота. Бот реагирует только на три фразы:
    hello
    bye
    what's up
    Соответственно, его ответами будут :
    Hi, I'm Bot
    Bye-bye
    sup-sup

    Примечание:
    ваша задача - использовать PartialFunction
    и неплохо бы было предусмотреть, чтобы никаких MatchError не возникало (решение, где вручную указывается значение по умолчанию - не подойдет)
    позаботьтесь, чтобы обратиться к функции можно было через переменную типа Option[String] с именем chatbot - иначе ваш код не пройдет тесты
    подумайте, как решить задачу не через isDefinedAt()
   */
  val chat:  PartialFunction[String, String] = {
    case "hello" => "Hi, I'm Bot"
    case "bye" => "Bye-bye"
    case "what's up" => "sup-sup"
  }

  val chatbot = chat.lift
  //println(chatbot("test"))
  //println(chatbot("hello"))
  //readLine.split(",").map(chatbot).foreach(println)
  //scala.io.Source.stdin.getLines().foreach(line => println(chatbot(line)))
  //scala.io.Source.stdin.getLines().map(chatbot).foreach(println)

  /**
  Приведем несколько примеров:
    результатом сравнения 1.0.2.03 и 1.1.0 будет -1 (1.0.2.03 < 1.1.0, так как 0 < 1)
    результатом сравнения 2.1 и 2.01 будет 0 (не забудьте предусмотреть в программе случаи, когда номера начинаются с нулей)
    результатом сравнения 3.0 и 3.0.0.0 также будет 0
   */
  def compare(version1: String, version2: String): Int = {
    var v1 = version1.split("\\W+").map(num => num.toInt).toList
    var v2 = version2.split("\\W+").map(num => num.toInt).toList

    if(v1.length < v2.length) v1 = filler(v1, v2.length-v1.length)
    else v2 = filler(v2, v1.length-v2.length)

    comparator(v1, v2)
  }

  def comparator(v1List: List[Int], v2List: List[Int]): Int = {
    @tailrec
    def loop(count: Int): Int ={
      if(count == v1List.length) 0
      else if(more((v1List(count), v2List(count))) !=0) more((v1List(count), v2List(count)))
      else loop(count+1)
    }
    loop(0)
  }

  def filler(set: List[Int], length: Int): List[Int] = {
    def loop(count: Int, acc: List[Int] = set): List[Int] ={
      if(count == length) acc
      else loop(count+1, acc :+ 0)
    }
    loop(0)
  }

  val equal: PartialFunction[(Int, Int), Int] = {
    case Tuple2(a, b) if a == b => 0
  }

  val more: PartialFunction[(Int, Int), Int] = equal.orElse[(Int, Int), Int] {
    case Tuple2(a, b) if a > b => 1
    case Tuple2(a, b) if a < b => -1
  }

  println(compare("1.0.2", "1.1.0")) // -1
  println(compare("2.001", "2.01")) // 0
  println(compare("1.0.2.03", "1.1.0"))  // -1
  println(compare("3.0", "3.0.0.0"))  // 0
  println(compare("0.9", "1.0")) // -1
  println(compare("1.9", "1.0"))  // 1
  println(compare("3.0", "3.0.0.1"))  // -1

}
