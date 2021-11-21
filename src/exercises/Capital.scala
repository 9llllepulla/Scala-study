package exercises

object Capital extends App {

  def isCapital(word: String, pos: Int): Boolean = {
    if(pos >= 0 && word.length > pos) word.charAt(pos).isUpper
    else false
  }

  println(isCapital("reSteR", 5)) // true
}
