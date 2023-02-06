package exercises.conecenter

case class Node(override val value: Char, override val leftChild: KuuseTree, override val rightChild: KuuseTree) extends KuuseTree {

  override val root: KuuseTree = null

  override def isEmpty: Boolean = false

  override def isLeaf: Boolean = leftChild.isEmpty && rightChild.isEmpty

  override def asList: List[String] = ???

  override def buildMessageTree(message: String, codeTree: KuuseTree): KuuseTree = ???


  override def buildTree(code: List[String]): KuuseTree = {
    val codes = slice(code)
    ???
  }

  // A( B( C(A) ), D(A) )
  def slice(code: List[String]): List[List[String]] = {
    var slicedCode: List[List[String]] = List()

    def loop(codes: List[String], prefixCount: Int = 0): Unit = {
      val count = codes.head.count(ch => ch == '-')
      if (codes.nonEmpty) {
        if (prefixCount < count) slicedCode = slicedCode ++ List(codes)
        else loop(codes.tail, count)
      }
    }
    loop(code)
    slicedCode
  }

}
