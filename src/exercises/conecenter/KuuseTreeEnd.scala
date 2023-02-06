package exercises.conecenter

case object KuuseTreeEnd extends KuuseTree {
  override def value: Nothing = throw new NoSuchElementException

  override def leftChild: KuuseTree = throw new NoSuchElementException

  override def rightChild: KuuseTree = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def isLeaf: Boolean = false

  override def asList: List[String] = List()

  override def buildMessageTree(message: String, codeTree: KuuseTree): KuuseTree = throw new NoSuchElementException

  override def buildTree(code: List[String]): KuuseTree = throw new NoSuchElementException

  override val root: KuuseTree = throw new NoSuchElementException
}
