package theory.constructions
///////////////////////////////////////////////////////////////////////////
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
/////////////////////////////////////////////////////////////////////////////

class Point(val x: Int, val y: Int)

class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular {

  // реализованы в трейте
  /*def left: Int = topLeft.x
  def right: Int = bottomRight.x
  def width: Int = right - left*/
}

abstract class Component extends Rectangular {

  // реализованы в трейте
  /*def topLeft: Point
  def bottomRight: Point

  def left: Int = topLeft.x
  def right: Int = bottomRight.x
  def width: Int = right - left*/
}

trait Rectangular {

  def topLeft: Point
  def bottomRight: Point

  def left: Int = topLeft.x
  def right: Int = bottomRight.x
  def width: Int = right - left
}
///////////////////////////////////////////////////////////////////////////
object Traites extends App {

  val frog = new Frog()
  frog.philosophidze() // Мне живется не легко, потому что я зеленая!

  val rect = new Rectangle(new Point(1, 1), new Point(10, 10))
}
