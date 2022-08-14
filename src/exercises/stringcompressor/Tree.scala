package exercises.stringcompressor


/**
 * Дерево с узлами данных.
 *
 * Изменяемый объект, необходим для создания приращения дерева
 */
sealed case class Tree(var root: Node) extends Ordered[Tree] {

  def this(leftNode: Node, rightNode: Node, frequencyValue: Int) = {
    this(new Node())
    root.leftNode = leftNode
    root.rightNode = rightNode
    root.frequencyValue = frequencyValue
  }

  override def compare(that: Tree): Int = {
    that.root.frequencyValue - this.root.frequencyValue
  }
}

object Tree {

  /**
   * Преобразование 2 деревьев в 1 новое дерево с суммарной частотой
   * двух входных деревьев.
   *
   * @return преобразованное дерево
   */
  def transform(first: Tree, second: Tree): Tree = {
    // установка кода левого узла
    val left = first.root
    left.code = "0"
    // установка кода правого узла
    val right = second.root
    right.code = "1"

    new Tree(left, right, first.root.frequencyValue + second.root.frequencyValue)
  }
}
