package exercises

import org.scalatest.flatspec.AnyFlatSpec

class NodeTest extends AnyFlatSpec {

  behavior of "NodeTest"

  it should "collectLeaves" in {
    val tree = Node(1, Node(2, Node(4, TreeEnd, TreeEnd), Node(5, TreeEnd,
      Node(8, TreeEnd, TreeEnd))), Node(3, Node(7, TreeEnd, TreeEnd), Node(6, TreeEnd, TreeEnd)))

    assert(tree.collectLeaves == List(Node(4,TreeEnd,TreeEnd), Node(7,TreeEnd,TreeEnd), Node(6,TreeEnd,TreeEnd), Node(8,TreeEnd,TreeEnd)))
    assert(tree.collectLeaves.sortWith(_.value < _.value).flatMap(leaf => List(leaf.value)) == List(4, 6, 7, 8))
  }

  it should "countLeaves" in {
    val tree = Node(1, Node(2, Node(4, TreeEnd, TreeEnd), Node(5, TreeEnd,
      Node(8, TreeEnd, TreeEnd))), Node(3, Node(7, TreeEnd, TreeEnd), Node(6, TreeEnd, TreeEnd)))

    assert(tree.countLeaves == 4)
  }

  it should "nodesAtLevel" in {
    val tree = Node(1,
      Node(2,
        Node(4,
          TreeEnd,
          TreeEnd),
        Node(5,
          TreeEnd,
          Node(8,
            TreeEnd,
            TreeEnd))),
      Node(3,
        Node(6,
          TreeEnd,
          TreeEnd),
        Node(7,
          TreeEnd,
          TreeEnd)))
    assert(tree.nodesAtLevel(0) == List(Node(1,Node(2,Node(4,TreeEnd,TreeEnd),Node(5,TreeEnd,Node(8,TreeEnd,TreeEnd))),Node(3,Node(6,TreeEnd,TreeEnd),Node(7,TreeEnd,TreeEnd)))))
    assert(tree.nodesAtLevel(2) == List(Node(4,TreeEnd,TreeEnd), Node(5,TreeEnd,Node(8,TreeEnd,TreeEnd)), Node(6,TreeEnd,TreeEnd), Node(7,TreeEnd,TreeEnd)))
    assert(tree.nodesAtLevel(-1) == List())
    assert(tree.nodesAtLevel(3) == List(Node(8, TreeEnd, TreeEnd)))
    assert(tree.nodesAtLevel(5) == List())
  }

  it should "collectNodes" in {
    val tree = Node(1, Node(2, Node(4, TreeEnd, TreeEnd), Node(5, TreeEnd, Node(8, TreeEnd, TreeEnd))), Node(3, Node(6,
      TreeEnd, TreeEnd), Node(7, TreeEnd, TreeEnd)))
    assert(tree.collectNodes() == List(1, 2, 4, 5, 8, 3, 6, 7))
  }

  it should "hasPath" in {
    val tree = Node(1, Node(2, Node(4, TreeEnd, TreeEnd), Node(5, TreeEnd, Node(8, TreeEnd, TreeEnd))), Node(3, Node(6,
      TreeEnd, TreeEnd), Node(7, TreeEnd, TreeEnd)))
    assert(tree.hasPath(tree, 7))
    assert(!tree.hasPath(tree, 3))
    assert(tree.hasPath(tree, 11))
    assert(tree.hasPath(tree, 16))
    assert(tree.hasPath(tree, 10))
  }

  it should "findAllPaths" in {
    val tree = Node("1", Node("2", Node("4", TreeEnd, TreeEnd), Node("5", TreeEnd, Node("8", TreeEnd, TreeEnd))), Node("3", Node("6",
      TreeEnd, TreeEnd), Node("7", TreeEnd, TreeEnd)))
    assert(tree.findAllPaths(tree, "10") == List(List("1", "3", "6")))
    assert(tree.findAllPaths(tree, "16") == List(List("1", "2", "5", "8")))
    assert(tree.findAllPaths(tree, "3") == List())
  }

}
