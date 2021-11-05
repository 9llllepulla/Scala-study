package theory.constructions

object Companions extends App {

  /**
   *  apply можно не прописывать, а сразу после имени объекта в скобках
   *  указывать параметры apply метода
   *  переименовав getString в toString, мы избавили себя от необходимости вообще
   *  прописывать имя метода для его вызова
   */
  println(MyCompanion("hello", "world")) // helloworld
  println(MyCompanion("welcome")) // welcomeextraData

}

// Классы-компаньоны
class MyCompanion(val str: String) {
  private var extra = "extraData"
  override def toString: String = str + extra
}

object MyCompanion {
  def apply(base: String, extras: String): MyCompanion = {
    val s = new MyCompanion(base)
    s.extra = extras
    s
  }
  def apply(base: String) = new MyCompanion(base)
}
