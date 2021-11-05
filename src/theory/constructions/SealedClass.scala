package theory.constructions

class SealedClass {
}

/**
 * использование ключевого слова sealed для класса
 * (это более мягкая версия final, поэтому допускается extends в текущем файле,
 * но воспрещается вне этого файла)
 */
sealed class Person(name: String, age: Int) {
  val gender = "n/a"

  def greet: String = s"Hello"

  def this() = this("UNKNOWN", 0)
}

class Student(name: String, id: Int, age: Int, stGender: String) extends Person {
  override val gender: String = stGender

  override def greet: String = s"${super.greet}, $name"
}
