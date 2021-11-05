package theory.difficult

object SyntacticSugar extends App {

  val bob = new PersonSugar("Bob", "Developer")
  // равные вызовы методов Infix notation
  println(bob.worksAs("Developer"))
  println(bob worksAs "Developer")

  // operators
  val alice = new PersonSugar("Alice", "Manager")
  // равные вызовы методов Infix notation
  println(bob.&(alice))
  println(bob & alice)

  // apply равнозначные вызовы
  println(bob.apply())
  println(bob())

  val person = new PersonPlus("Bob")
  println((+person).name)

}

class PersonSugar(val name: String, occupation: String) {
  def worksAs(jobName: String): Boolean = jobName == occupation

  def &(person: PersonSugar): String = {
    s"${this.name} and ${{person.name}}"
  }

  def apply(): String = s"$name works as $occupation"
}

class PersonPlus(val name: String) {
  def unary_+():PersonPlus = new PersonPlus(name++" NoSurname")
}
