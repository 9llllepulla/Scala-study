package exercises

import org.scalatest.BeforeAndAfterEach
import org.scalatest.flatspec.AnyFlatSpec

class VersionComparatorTest extends AnyFlatSpec with BeforeAndAfterEach {

  behavior of "VersionComparatorTest"

  it should "splitter" in {
    assertResult(List(1, 0, 2, 5))(VersionComparator.splitter("1.0.2.5"))
  }

  it should "zeroExtender" in {
    assertResult(List(1, 0, 0, 0, 0))(VersionComparator.zeroExtender(List(1), 5))
    assertResult(List(1, 3))(VersionComparator.zeroExtender(List(1, 3), 2))
    assertResult(List(1, 3, 5))(VersionComparator.zeroExtender(List(1, 3, 5), 2))
  }

  it should "compare" in {
    assertResult(-1)(VersionComparator.compare("1.0.2.03", "1.1.0"))
    assertResult(0)(VersionComparator.compare("3.0", "3.0.0.0"))
    assertResult(-1)(VersionComparator.compare("3.0", "3.0.0.1"))
  }

  it should "comparator" in {
    assertResult(-1)(VersionComparator.comparator(List(1, 0, 2), List(1, 1, 0)))
  }
}
