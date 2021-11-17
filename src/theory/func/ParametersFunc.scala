package theory.func

object ParametersFunc extends App {

  /**
   * Повторяющиеся параметры
   */
  // аргументы переменной длины
  def echo(args: String*): Unit =
    for(arg <- args) print(arg)

  echo("one") // one
  echo("hello ", "world!") // hello world!

  // чтобы передать массив в эту функцию
  val seq = Seq("What's ", "up ", "doc ?")
  echo(seq: _*) // What's up doc ?

  /**
   * Именованные аргументы
   */
  def speed(distance: Float, time: Float): Float = distance / time
  // обычный вызов
  speed(100, 100) // 10.0
  // именованный вызов аргументов
  speed(distance = 100, time = 10) // 10.0
  speed(time = 10, distance = 100) // 10.0

  /**
   * Параметры по-умолчанию
   */
  def speed100(time: Float, distance: Float = 100): Float = distance / time
  speed100(50) // 2.0
  speed100(200, 1000) // 5.0

}
