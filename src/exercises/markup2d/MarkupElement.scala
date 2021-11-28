package exercises.markup2d

import exercises.markup2d.MarkupElement.elem

/**
 * Элементы разметки
 * Двумерные прямоугольники из символов
 */
abstract class MarkupElement {

  /**
   * Содержимое элемента разметки
   *
   * @return массив строк, где каждая строка обозначает ряд
   */
  def contents: Array[String]

  /**
   * Высота элемента
   */
  def height: Int = contents.length

  /**
   * Ширина элемента (длина первого ряда)
   */
  def width: Int = contents(0).length

  /**
   * Возвращает представление элемента в виде строки
   * */
  override def toString: String = contents mkString "\n"

  /**
   * Создание нового элемента путем объединения с другим элементом, находящимся рядом
   * */
  def beside(that: MarkupElement): MarkupElement = {
    val this1 = this heighten that.height
    val that1 = that heighten this.height
    elem(
      for (
        (line1, line2) <- this1.contents zip that1.contents
      ) yield line1 + line2
    )
  }

  /**
   * Создание нового элемента путем помещения одного элемента выше другого
   * */
  def above(that: MarkupElement): MarkupElement = {
    val this1 = this widen that.width
    val that1 = that widen this.width
    elem(this1.contents ++ that1.contents)
  }

  /**
   * Метод получает ширину и возвращает объект указанной ширины
   * В результате создается объект, в котором содержимое отцентрировано за счет отступов справа и слева
   * с помощью пробелов
   */
  def widen(w: Int): MarkupElement = {
    if (w <= width) this
    else {
      val left = elem(' ', (w - width) / 2, height)
      val right = elem(' ', w - width - left.width, height)
      left beside this beside right
    }
  }

  /**
   * Метод получает высоту и возвращает объект указанной высоты
   * В результате создается объект, в котором содержимое отцентрировано за счет отступов вверху и внизу
   * с помощью пробелов
   */
  def heighten(h: Int): MarkupElement = {
    if (h <= height) this
    else {
      val top = elem(' ', width, (h - height) / 2)
      val bottom = elem(' ', width, h - height - top.height)
      top above this above bottom
    }
  }
}

/**
 * Фабричный объект для конструирования объектов
 * */
object MarkupElement {

  def elem(contents: Array[String]): MarkupElement =
    new ArrayMarkupElement(contents)

  def elem(chr: Char, width: Int, height: Int): MarkupElement =
    new UniformMarkupElement(chr, width, height)

  def elem(line: String): MarkupElement =
    new LineMarkupElement(line)

  /**
   * Обращение к классам через фабричные методы
   * */

  /*
   * поле с тем же именем может быть реализацией метода из родительского класса
   */
  private class ArrayMarkupElement(val contents: Array[String]) extends MarkupElement

  /*
   * Элемент разметки
   * Содержит один ряд, задаваемый строкой
   */
  private class LineMarkupElement(s: String) extends MarkupElement {

    override def width: Int = s.length

    override def height: Int = 1

    val contents: Array[String] = Array(s)
  }

  /*
   * Форма заполненная заданным символом
   */
  private class UniformMarkupElement(
                        ch: Char,
                        override val width: Int,
                        override val height: Int
                      ) extends MarkupElement {

    private val line = ch.toString * width

    def contents: Array[String] = Array.fill(height)(line)
  }
}
