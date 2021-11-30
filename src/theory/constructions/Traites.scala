package theory.constructions

trait Philosophical {

  def philosophidze() = {
    println("На меня тратится память, следовательно, я существую!")
  }
}

class Frog extends Animal with Philosophical {
  override def toString: String = "зеленая"

  override def philosophidze(): Unit = {
    println("Мне живется не легко, потому что я " + toString + "!")
  }
}

class Animal
trait HasLeg

object Traites extends App {

  val frog = new Frog()
  frog.philosophidze() // Мне живется не легко, потому что я зеленая!

}

