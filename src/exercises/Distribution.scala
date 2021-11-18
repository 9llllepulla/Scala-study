package exercises

object Distribution {

  /**
   * рассчитать плотность вероятности нормального распределения по заданным математическому ожиданию
   */
  def normalDistribution(mu: Double, sigma: Double, x: Double): Double = {
    val power = -1 * Math.pow(x - mu, 2) / (2 * Math.pow(sigma, 2))
    val powerE = Math.pow(Math.E, power)
    val first = 1 / (sigma * Math.sqrt(2*Math.PI))
    first * powerE
  }
  // вариант решения с символами
  import scala.math.{Pi => π, sqrt => √, exp => Exp, pow}
  def normalDistrib(μ: Double, σ: Double, x: Double): Double = {
    Exp( -(pow((x -  μ), 2)) / (2 * σ * σ) ) / (σ * √(2 * π))
  }

}
