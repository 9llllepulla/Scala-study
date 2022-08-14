package exercises.stringcompressor

/**
 * Узел дерева с данными
 * */
sealed class Node(var char: Char, var frequencyValue: Int) {

  var code: String = ""
  var leftNode: Node = _
  var rightNode: Node = _

  def this() = this(" ".charAt(0), 0)

  def isLeaf: Boolean = leftNode == null && rightNode == null
}
