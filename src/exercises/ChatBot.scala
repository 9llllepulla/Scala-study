package exercises

import scala.io.StdIn.readLine

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
  println(chatbot("test"))
  println(chatbot("hello"))
  readLine.split(",").map(chatbot).foreach(println)
  scala.io.Source.stdin.getLines().foreach(line => println(chatbot(line)))
  // или
  scala.io.Source.stdin.getLines().map(chatbot).foreach(println)

}
