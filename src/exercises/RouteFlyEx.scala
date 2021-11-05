package exercises

import scala.annotation.tailrec
import scala.util.Try

/**
 *  Разработка маршрутной сети для авиакомпании.
 *  Сеть будет хранить ассоциации между пунктом отправления и всеми доступными для него пунктами назначения.
 *  Чтобы эти ассоциации создать, нужно сначала предоставить системе список возможных локаций.
 *  Необходимо реализовать не только основные методы для работы с сетью, но и дополнительные
 */
object RouteFlyEx extends App {

  /** Основные методы */
  // добавляет в network новую пару, в которой ключом является location. Map(location -> Set())
  def add(network: Map[String, Set[String]], location: String): Map[String, Set[String]] = {
    network + (location -> Set())
  }

  // удаляет локацию из маршрутной сети
  def remove(network: Map[String, Set[String]], location: String): Map[String, Set[String]] = {
    @tailrec
    def loop(destinations: Set[String], acc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if(destinations.isEmpty) acc
      else loop(destinations.tail, disconnect(acc, location, destinations.head))
    }
    val disconnected = loop(network(location), network)
    disconnected - location
  }

  // связывает две локации между собой (учитывать, что связь действует в обе стороны, т.е. становится доступным маршрут как туда, так и обратно)
  def connect(network: Map[String, Set[String]], pointA: String, pointB: String): Map[String, Set[String]] = {
    network + (pointA -> (network(pointA) + pointB)) + (pointB -> (network(pointB) + pointA))
  }

  // удаляет маршрут
  def disconnect(network: Map[String, Set[String]], pointA: String, pointB: String): Map[String, Set[String]] = {
    network + (pointA -> (network(pointA) - pointB)) + (pointB -> (network(pointB) - pointA))
  }

  /** Дополнительные методы */
  // возвращает количество доступных прямых перелетов из заданной точки
  def nFlights(network: Map[String, Set[String]], location: String): Int = {
    network(location).size
  }

  // возвращает точку, из которой доступно больше всего прямых перелетов
  def mostFlights(network: Map[String, Set[String]]): String = {
    network.keySet.maxBy(key => network(key).size)
  }

  // возвращает количество точек, не включенных ни в один маршрут
  def nLocationsWithNoFlights(network: Map[String, Set[String]]): Int = {
    //варианты функций с тем же результатом
    //network.view.filter(pair => pair._2.isEmpty).size
    //network.count(_._2.isEmpty)
    //network.view.filterKeys(key => network(key).size == 0).size
    //network.view.filterKeys(key => network(key).isEmpty).size
    //network.view.filterKeys(key => network(key).isEmpty).size
    network.count(pair => pair._2.isEmpty)
  }

  // проверяет, связаны ли две точки между собой (учитывать возможные пересадки, необходимые чтобы перелететь из одной точки в другую)
  def isConnected(network: Map[String, Set[String]], pointA: String, pointB: String): Boolean = {
    @tailrec
    def loop(path: Set[String], visited: Seq[String], next: Seq[String] = Seq()): Boolean = {
      if(path.isEmpty && next.isEmpty) false
      else if(path.isEmpty) loop(next.toSet, visited)
      else if(Try(network(path.head)).isSuccess & network(path.head).contains(pointB)) true
      else {
        val nextVisited = visited ++ Seq(path.head)
        loop(path.tail, nextVisited, pathToQueue(network, path.head, nextVisited, next))
      }
    }
    if(Try(network(pointA)).isSuccess & network(pointA).contains(pointB)) true
    else loop(network(pointA), Seq(pointA))
  }
  /*  чужое краткое решение
  def isConnected(network: Map[String, Set[String]], pointA: String, pointB: String): Boolean = {
    if (network(pointA).contains(pointB)) true
    else network(pointA).flatMap(x => network(x)).contains(pointB)
  }*/

  private def pathToQueue(network: Map[String, Set[String]], location: String, visited: Seq[String], queue: Seq[String]): Seq[String] = {
    val path = locationPath(network, location)
    queue ++ path.filter(key => !visited.contains(key))
  }

  private def locationPath(network: Map[String, Set[String]], location: String): Set[String] = {
    if(Try(network(location)).isSuccess) network(location)
    else Set()
  }
}
