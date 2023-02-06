package exercises

import scala.annotation.tailrec
import scala.util.Try

object Main extends App {

  val tree = Node(1, Node(2, Node(4, TreeEnd, TreeEnd), Node(5, TreeEnd,
    Node(8, TreeEnd, TreeEnd))), Node(3, Node(7, TreeEnd, TreeEnd), Node(6, TreeEnd, TreeEnd)))

  println(tree.collectLeaves.sortWith(_.value < _.value).flatMap(leaf => List(leaf.value)))
}

/**
 * Бинарное дерево
 *
 * @tparam T
 */
abstract class BinaryTree[+T] {
  def value: T

  def leftChild: BinaryTree[T]

  def rightChild: BinaryTree[T]

  def isEmpty: Boolean

  def isLeaf: Boolean

  def collectLeaves: List[BinaryTree[T]]

  def countLeaves: Int

  def nodesAtLevel(level: Int): List[BinaryTree[T]]

  def collectNodes(): List[T]

  def hasPath(tree: BinaryTree[Int], target: Int): Boolean

  def findAllPaths(tree: BinaryTree[String], target: String): List[List[String]]
}

case object TreeEnd extends BinaryTree[Nothing] {
  override def value: Nothing = throw new NoSuchElementException

  override def leftChild: BinaryTree[Nothing] = throw new NoSuchElementException

  override def rightChild: BinaryTree[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def isLeaf: Boolean = false

  override def collectLeaves: List[BinaryTree[Nothing]] = List()

  override def countLeaves: Int = 0

  override def nodesAtLevel(level: Int): List[BinaryTree[Nothing]] = List()

  override def collectNodes(): List[Nothing] = List()

  override def hasPath(tree: BinaryTree[Int], target: Int): Boolean = false

  override def findAllPaths(tree: BinaryTree[String], target: String): List[List[String]] = List()
}

/**
 * Узел дерева
 */
case class Node[+T](override val value: T, override val leftChild: BinaryTree[T], override val rightChild: BinaryTree[T]) extends BinaryTree[T] {

  override def isEmpty: Boolean = false

  override def isLeaf: Boolean = leftChild.isEmpty && rightChild.isEmpty

  // общее количество листьев, присутствующих в дереве
  override def countLeaves: Int = collectLeaves.count(_ => true)

  // список значений узлов дерева на заданном уровне
  override def nodesAtLevel(level: Int): List[BinaryTree[T]] = {
    @tailrec
    def loop(nodes: List[BinaryTree[T]] = List(this), count: Int = 0): List[BinaryTree[T]] = {
      if (level < 0) List()
      else if (count == level || nodes.isEmpty) nodes
      else loop(nodes.flatMap(node => Try(Seq(node.leftChild)).getOrElse(Seq())
        ++ Try(Seq(node.rightChild)).getOrElse(Seq())).filter(child => !child.isEmpty), count + 1)
    }

    loop()
  }

  // Чужой вариант решения без рекурсии
  /*override def nodesAtLevel(level: Int): List[BinaryTree[T]] = level match {
    case 0 => List(this)
    case x => this.leftChild.nodesAtLevel(x-1) ++ this.rightChild.nodesAtLevel(x-1)
  }*/

  // значения всех узлов дерева
  override def collectNodes(): List[T] = {
    var nodes = List[T]()

    def collect(node: BinaryTree[T]): Unit = {
      if (!node.isEmpty) {
        nodes = nodes ++ List(node.value)
        collect(node.leftChild)
        collect(node.rightChild)
      }
    }

    collect(this)
    nodes
  }

  // Чужое решение (super !)
  //override def collectNodes(): List[T] = this.value :: this.leftChild.collectNodes ::: this.rightChild.collectNodes

  // получение списка всех листьев дерева
  override def collectLeaves: List[BinaryTree[T]] = {
    @tailrec
    def loop(nodes: List[BinaryTree[T]] = List(this), leaves: List[BinaryTree[T]] = List()): List[BinaryTree[T]] = {
      if (nodes.isEmpty) leaves
      else if (!nodes.head.isLeaf) {
        val children = Try(Seq(nodes.head.leftChild)).getOrElse(Seq()) ++ Try(Seq(nodes.head.rightChild)).getOrElse(Seq())
        loop(nodes.tail ++ children, leaves)
      }
      else loop(nodes.tail, leaves ++ Seq(nodes.head))
    }

    loop()
  }

  // получить все имеющиеся в дереве пути от корня до листа, удовлетворяющие заданному условию
  // если в заданном дереве tree существует такой путь от корня до листа,
  // что сумма значений узлов, лежащих на этом пути, равняется заданному значению target.
  override def findAllPaths(tree: BinaryTree[String], target: String): List[List[String]] = {
    var allPaths: List[List[String]] = List()

    def loop(node: BinaryTree[String], path: List[String] = List()): Unit = {
      if (!node.isEmpty) {
        if (node.isLeaf) allPaths = allPaths ++ List(path ++ Seq(node.value))
        else {
          loop(node.leftChild, path ++ Seq(node.value))
          loop(node.rightChild, path ++ Seq(node.value))
        }
      }
    }

    loop(tree)
    allPaths.filter(path => path.map(str => str.toInt).sum == target.toInt)
  }

  // возвращает true, если в заданном дереве tree существует такой путь от корня до листа,
  // что сумма значений узлов, лежащих на этом пути, равняется заданному значению target.
  override def hasPath(tree: BinaryTree[Int], target: Int): Boolean = {
    var allPaths: List[List[Int]] = List()

    def loop(node: BinaryTree[Int] = tree, path: List[Int] = List()): Unit = {
      if (!node.isEmpty) {
        if (node.isLeaf) allPaths = allPaths ++ List(path ++ Seq(node.value))
        else {
          loop(node.leftChild, path ++ Seq(node.value))
          loop(node.rightChild, path ++ Seq(node.value))
        }
      }
    }

    loop()
    allPaths.map(path => path.sum).contains(target)
  }

  // другое решение
  /*
    def hasPath(tree: BinaryTree[Int], target: Int): Boolean = {
      if (tree.isEmpty) target == 0
      else hasPath(tree.leftChild, target - tree.value) || hasPath(tree.rightChild, target - tree.value)
   }
   */
}

