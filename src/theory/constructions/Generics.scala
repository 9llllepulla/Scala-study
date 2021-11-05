package theory.constructions

object Generics {

  val invariantFruit: InvariantList[Fruit] = new InvariantList[Fruit]

  val covariantFruit: CovariantList[Fruit] = new CovariantList[Apple]

  val contraVariant: ContraVariantList[Apple] = new ContraVariantList[Fruit]

}
sealed class Fruit
class Apple extends Fruit
class Banana extends Fruit

class InvariantList[A]

class CovariantList[+A]

class ContraVariantList[-A]
