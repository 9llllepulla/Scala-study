package exercises

import Increase.increase
import Increase.createIncreaseResultString
import org.scalatest.funsuite.AnyFunSuiteLike

class IncreaseTest extends AnyFunSuiteLike {

  test("test createIncreaseResultString") {
    assertResult("23 23")(createIncreaseResultString(23))
    assertResult("1")(createIncreaseResultString(1))
    assertResult("345 345 345")(createIncreaseResultString(345))
  }

  test("test Increase") {
    assertResult(23)(increase(3, 5, 4))
    assert(increase(3, 5, 4) != 22)
    assertResult(2)(increase(1, 1, 1))
  }
}
