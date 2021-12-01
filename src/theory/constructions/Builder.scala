package theory.constructions

/**
 * Реализация паттерна Билдер
 */
trait Configured
trait NonConfigured

class Builder[A] private() {

  def configure(): Builder[Configured] = new Builder[Configured]

  /*
    Контролем типа в данном примере занимается «оператор» =:=.
    Запись A =:= B говорит о том, что параметрический (generic) тип A должен быть равен типу B.
  */
  def build()(implicit ev: Builder[A] =:= Builder[Configured]): Unit = {
    println("It's Work!")
  }
}

object Builder {

  def apply(): Builder[NonConfigured] = {
    new Builder[NonConfigured]()
  }
}

object Main extends App {

  val build = Builder()
    .configure()
    .build()
}
