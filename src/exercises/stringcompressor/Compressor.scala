package exercises.stringcompressor

import exercises.stringcompressor.Compressor.{toCodeTable, toHuffmanTree}

import java.io.File
import java.nio.file.Files
import scala.annotation.tailrec
import scala.collection.mutable
import scala.jdk.CollectionConverters.CollectionHasAsScala

case class Compressor(text: String) {

  /** количество дубликатов символов в строке */
  private val duplicateCharsCount: Map[Char, Int] = text.toList.groupMapReduce(identity)(_ => 1)(_ + _)

  /** список частотности */
  private val frequencyList: List[Node] = {
    var nodeList = List[Node]()
    text.toSet.foreach((ch: Char) => nodeList = new Node(ch, duplicateCharsCount(ch)) :: nodeList)
    nodeList
  }

  private val oneNodeTrees = frequencyList.map(node => Tree(node))

  private val priorityQueue = {
    val pQ = new mutable.PriorityQueue[Tree]()
    pQ.enqueue(oneNodeTrees: _*)
    pQ
  }

  private val huffmanTree: Tree = toHuffmanTree(priorityQueue)

  private val codeTable: Map[Char, String] = toCodeTable(huffmanTree)

}

object Compressor {

  private var compressor: Compressor = _
  private var hTree: Tree = _

  /**
   * Кодирование входящего текста алгоритмом Хаффмана.
   *
   * @param text входящий текст
   * @return закодированная строка в двоичном представлении.
   */
  def code(text: String): String = {
    compressor = new Compressor(text)
    hTree = compressor.huffmanTree
    val builder = new StringBuilder
    text.toList
      .map(ch => compressor.codeTable(ch))
      .foreach(code => builder.append(code))
    builder.toString()
  }

  def code(file: File): String = {
    if (file.exists() && file.getName.endsWith(".txt")) {
      val fileText = Files.readAllLines(file.toPath)
        .asScala
        .toList
        .foldLeft("")(_ + _)
      this.code(fileText)
    }
    else ""
  }

  /**
   * Декодирование входящего двоичного кода
   *
   * @param code двоичное представление текста, закодированного
   *             кодом Хаффмана.
   * @return декодированный текст
   */
  def decode(code: String): String = {
    if (code.nonEmpty || !code.isBlank) {
      val builder = new StringBuilder
      var currentNode = hTree.root
      for (ch <- code.toList) {
        if (ch == '0') currentNode = currentNode.leftNode
        if (ch == '1') currentNode = currentNode.rightNode
        if (currentNode.isLeaf) {
          builder.append(currentNode.char)
          currentNode = hTree.root
        }
      }
      builder.toString()
    }
    else ""
  }

  def uncompressedLength: Long = compressor.text.toList
    .map(ch => Integer.toBinaryString(ch))
    .foldLeft("")(_ + _)
    .length

  def compressedLength: Long = this.code(compressor.text).length

  def compressRate: Float = uncompressedLength / (1f * compressedLength)

  /**
   * Создание дерева Хаффмана путем построения дерева из извлеченных элементов
   * приоритетной очереди (деревьев) и объединения их в одно дерево,
   * с внесением, вновь образованного дерева, в приоритетную очередь.
   * Т.о. количество деревьев в приоритетной очереди сокращается, а деревья,
   * вставляемые в очередь растут.
   * Единственное оставшееся в очереди дерево является деревом Хаффмана.
   *
   * @param priorityQueue приоритетная очередь деревьев
   * @return
   */
  private def toHuffmanTree(priorityQueue: mutable.PriorityQueue[Tree]): Tree = {
    @tailrec
    def loop(priorityQueue: mutable.PriorityQueue[Tree], growingTree: Tree = null): Tree = {
      if (priorityQueue.isEmpty) growingTree
      else {
        val oneTree = priorityQueue.dequeue()

        if (priorityQueue.nonEmpty) {
          val twoTree = priorityQueue.dequeue()
          val unionTree = Tree.transform(oneTree, twoTree)
          priorityQueue.enqueue(unionTree)
          loop(priorityQueue, unionTree)
        }
        else growingTree
      }
    }

    loop(priorityQueue)
  }

  /**
   * Рекурсивный обход дерева Хаффмана, начиная с корня,
   * где в процессе перемещения к листовым узлам сохраняется выбор
   * направления на каждой "развилке".
   *
   * @param hTree дерево Хаффмана
   * @return
   */
  private def toCodeTable(hTree: Tree): Map[Char, String] = {
    val codeTable = mutable.Map[Char, String]()

    def loop(root: Node, code: String = ""): Unit = {
      if (root.isLeaf) {
        codeTable += (root.char -> (code + root.code))
      }
      else {
        val growableCode = code + root.code
        loop(root.leftNode, growableCode)
        loop(root.rightNode, growableCode)
      }
    }

    loop(hTree.root)
    codeTable.toMap
  }
}
