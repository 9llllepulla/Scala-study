package exercises

import org.scalatest.flatspec.AnyFlatSpec

import scala.Console.in

class RouteFlyExTest extends AnyFlatSpec {

  behavior of "RouteFlyExTest"

  it should "add" in {
    assert(RouteFlyEx.add(Map(), "A") == Map("A" -> Set()))
    assert(RouteFlyEx.add(Map("A" -> Set()), "B") == Map("A" -> Set(), "B" -> Set()))
  }

  it should "isConnected" in {
    val testNetwork: Map[String, Set[String]] = Map("A" -> Set("B", "D", "E", "C"),
      "B" -> Set("A"), "C" -> Set("A"), "D" -> Set("A"), "E" -> Set("A"), "F" -> Set.empty[String])
    println(testNetwork) // Map(A -> Set(C), B -> Set(A), C -> Set(A))
    assert(RouteFlyEx.isConnected(testNetwork, "B", "C"))
    assert(RouteFlyEx.isConnected(testNetwork, "C", "B"))
    assert(RouteFlyEx.isConnected(testNetwork, "A", "E"))
  }

  it should "nFlights" in {
    val testNetwork = Map("A" -> Set("B"), "B" -> Set("A", "C"), "C" -> Set("A", "B", "D"))
    assert(RouteFlyEx.nFlights(testNetwork, "A") == 1)
    assert(RouteFlyEx.nFlights(testNetwork, "B") == 2)
    assert(RouteFlyEx.nFlights(testNetwork, "C") == 3)
  }

  it should "connect" in {
    val testNetwork = Map("A" -> Set("testA"), "B" -> Set("testB"))
    val expectedNetwork = Map("A" -> Set("testA","B"), "B" -> Set("testB","A"))
    println(expectedNetwork) // Map(A -> Set(testA, B), B -> Set(testB, A))

    val testNetwork2 = Map("A" -> Set.empty[String], "B" -> Set.empty[String])
    val expectedNetwork2 = Map("A" -> Set("B"), "B" -> Set("A"))
    println(expectedNetwork2) // Map(A -> Set(B), B -> Set(A))

    assert(RouteFlyEx.connect(testNetwork, "A", "B") == expectedNetwork)
    assert(RouteFlyEx.connect(testNetwork2, "A", "B") == expectedNetwork2)
  }

  it should "disconnect" in {
    val testNetwork = Map("A" -> Set("testA","B"), "B" -> Set("testB","A"))
    val expectedNetwork = Map("A" -> Set("testA"), "B" -> Set("testB"))
    assert(RouteFlyEx.disconnect(testNetwork, "A", "B") == expectedNetwork)
  }

  it should "remove" in {
    val testNetwork = Map("A" -> Set("B"), "B" -> Set("A"))
    val expectedNetwork = Map("B" -> Set())
    assert(RouteFlyEx.remove(testNetwork, "A") == expectedNetwork)
  }

  it should "nLocationsWithNoFlights" in {
    val testNetwork = Map("A" -> Set("testA","B"), "B" -> Set("testB","A"), "C" -> Set.empty[String], "D" -> Set.empty[String])
    println(testNetwork)
    assert(RouteFlyEx.nLocationsWithNoFlights(testNetwork) == 2)
  }

  it should "mostFlights" in {
    val testNetwork = Map("A" -> Set("B"), "B" -> Set("A", "C"), "C" -> Set("A", "B", "D"))
    val expected = "C"
    assert(RouteFlyEx.mostFlights(testNetwork) == expected)
  }
}
