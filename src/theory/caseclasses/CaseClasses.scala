package theory.caseclasses

/**
 * https://stepik.org/lesson/463106/step/1?unit=453731
 * Одной из основных идей функционального программирования является то,
 * что мы стараемся разделить структуры данных и операции над ними.
 * Т.е. все функции обычно сбрасываются в трейты и объекты,
 * но никак не в обычные классы (их вообще желательно избегать,
 * если того позволяет бизнес-логика вашего приложения, прибегая к case классам).
 * Однако существует набор методов, которые всегда придутся кстати для любого класса.
 * Именно доступ к таким методам и дают классы образцы, избавляя вас от необходимости имплементировать эти методы вручную.
 * Объявить класс образец очень легко. Надо всего лишь дописать ключевое слово case.
 *
 * Метод toString (который имеет свой синтаксический сахар, поэтому можно обойтись вообще без явного
 * прописывания имени этого метода в коде) избавляет вас от абракадабры, выводимой в обычном классе.
 *
 * Любой case класс имеет объект-компаньон (неявно)
 * В таком объекте-компаньоне всегда присутствует метод apply.
 */
object CaseClasses extends App {
  val smallLemur: Lemur = Lemur("Alusha", 1)
  val bigLemur: Lemur = Lemur("Sergio", 2)
  println(smallLemur)
  println(bigLemur)
  val copyLemur = smallLemur.copy(id = 2)
  println(copyLemur)

  val scalaCourse = Course("Scala", "Bob")
  val course = scalaCourse.copy("AdvancedScala")
  println(course)
}

case class Lemur(name: String, id: Int){

}
case class Course(title: String, instructor: String)

object Course {
  def apply(instructor: String): Course = Course("AdvancedScala", instructor)
}
