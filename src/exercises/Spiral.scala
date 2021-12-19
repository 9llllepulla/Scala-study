package exercises

import exercises.markup2d.MarkupElement

/**
 * Рисование спирали с помощью библиотеки MarkupElement
 */
object Spiral extends App {

  val space = MarkupElement.elem(" ")
  val corner = MarkupElement.elem("+")

  def spiral(nEdges: Int, directions: Int): MarkupElement = {
    if (nEdges == 1) MarkupElement.elem("+")
    else {
      val sp = spiral(nEdges - 1, (directions + 3) % 4)

      def verticalBar: MarkupElement = MarkupElement.elem('|', 1, sp.height)

      def horizontalBar: MarkupElement = MarkupElement.elem('-', sp.width, 1)

      if (directions == 0) (corner beside horizontalBar) above (sp beside space)
      else if (directions == 1) (sp above space) beside (corner above verticalBar)
      else if (directions == 2) (space beside sp) above (horizontalBar beside corner)
      else (verticalBar above corner) beside (space above sp)
    }
  }

  val nSides = 20//args(0).toInt
  println(spiral(nSides, 0))
}
