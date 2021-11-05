package theory.constructions

object Traites {

}

abstract class AbstractImpl(void: Unit) {

}

trait LikeJavaInterface {
  def realization: Int
}

trait ManyRealization {
  def realization: Int
}

class ImplementationTraits(void: Unit) extends LikeJavaInterface with ManyRealization {

  override def realization: Int = ???
}
