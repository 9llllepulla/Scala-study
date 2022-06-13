package theory.collections

import scala.collection.mutable.ArrayBuffer

object ArraysCollection extends App {

  val defaultFiveZeroArray = new Array[Int](5);
  println(defaultFiveZeroArray.mkString("Array(", ", ", ")")) // Array(0, 0, 0, 0, 0)

  val fiveElementArray = Array(1, 2, 3, 4, 5)
  println(fiveElementArray.mkString("Array(", ", ", ")")) // Array(1, 2, 3, 4, 5)

  val fiveElement = fiveElementArray(4)
  println(fiveElement) // 5

  defaultFiveZeroArray(0) = fiveElement
  println(defaultFiveZeroArray.mkString("Array(", ", ", ")")) // Array(5, 0, 0, 0, 0)

  /**
   * ArrayBuffer - изменяемый объект помогает эффективно работать с массивами, когда в них нужно добавлять элементы в начало и в конец массива
   */
  val buffer =  new ArrayBuffer[Int]()
  buffer += 12
  buffer += 15
  println(buffer) // ArrayBuffer(12, 15)
  20 +=: buffer
  println(buffer) // ArrayBuffer(20, 12, 15)
  println(buffer.toArray.mkString("Array(", ", ", ")")) // Array(20, 12, 15)



}
