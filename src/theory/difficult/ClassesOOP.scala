package theory.difficult

object ClassesOOP extends App {

  /*
  // 1 создание объекта
  val studentFullArgs = new StudentOOP(1, "Alla")
  // 2 создание объекта
  val studentOneArg = new StudentOOP("Sergei")
  // 3 создание объекта
  val studentWithoutArgs = new StudentOOP()
  */
  val instructor = new Instructor(1,"seRgey", "LYASHKO")
  println(instructor.fullName())

  val course = new CourseOOP(3, "title", "2020", instructor)
  println(course.getID)
  //println("old: "+course.releaseYear)
  //val newCourse = course.copyCourse("2021")
  //println("new: "+newCourse.releaseYear)

  //println("test instructor: "+course.isTaughtBy(instructor))

  //val nextCourse = new Course(1, "new", "2022", instructor)
  //println("test next instructor: "+nextCourse.isTaughtBy(instructor))


}
class CourseOOP(courseId: Int, title: String, releaseYear: String, instructor: Instructor){

  //def this(releaseYear: String) = this(0, title = "No title", releaseYear, instructor = null)

  def getID: String = courseId.toString++instructor.id.toString

  def isTaughtBy(instructor: Instructor): Boolean = instructor == this.instructor

  def copyCourse(newReleaseYear: String): CourseOOP = new CourseOOP(this.courseId, this.title, newReleaseYear, this.instructor)
}

class Instructor(val id: Int, name: String, surName: String){

  def fullName(): String = {
    val validName = if(isNameValid(name)) name else convert(name)
    val validSurName = if(isNameValid(surName)) surName else convert(surName)
    validName+" "+validSurName
  }

  private def isNameValid(name: String): Boolean = name.charAt(0).isUpper && name.toCharArray.forall(ch => ch.isLower)

  private def convert(name: String): String = name.charAt(0).toUpper+:name.substring(1).toLowerCase
}

// 1. объявление класса с конструктором
class StudentOOP(id: Int, val name: String){

  // 2. конструктор по-умолчанию без аргументов
  def this() = this(0, "NoName")

  // 3. конструктор с 1 аргументом name
  def this(name: String) = this(0, name)
}
