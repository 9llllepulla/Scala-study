package theory.collections

object SeqCollections extends App {

  // Seq
  val sequence = Seq(10, 20, 30, 40)
  println(sequence) // List(10, 20, 30, 40)
  val moreSequence = sequence ++ Seq(50, 60)
  println(moreSequence) // List(10, 20, 30, 40, 50, 60)
  println(moreSequence(3)) // 40
  println(moreSequence.search(60)) // Found(5)

}
