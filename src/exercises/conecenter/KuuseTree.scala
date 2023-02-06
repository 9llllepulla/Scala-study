package exercises.conecenter

import exercises.conecenter.KuuseTreeEnd.{buildMessageTree, buildTree}

abstract case class KuuseTree() {

  val root: KuuseTree

  def asList: List[String]

  def value: Char

  def leftChild: KuuseTree

  def rightChild: KuuseTree

  def isEmpty: Boolean

  def isLeaf: Boolean

  def buildMessageTree(message: String, codeTree: KuuseTree): KuuseTree

  def buildTree(code: List[String]): KuuseTree

}

object KuuseTree {

  def messageTree(message: String, code: List[String]): KuuseTree = {
    val codeTree = buildTree(code)
    buildMessageTree(message, codeTree)
  }

}
