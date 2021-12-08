package exercises

import exercises.Capital.isCapital
import org.scalatest.funsuite.AnyFunSuiteLike

class CapitalTest extends AnyFunSuiteLike {

  test("test is capital") {
    assert(isCapital("teSt", 2))
    assert(!isCapital("teSt", 1))
  }
}
