package com.github.dyamah.scalasom

import scala.util.Random

/**
  * Created by dyama on 1/19/16.
  */
class SOM (row : Int, column : Int) {
  private val matrix = {
    val init = Array.ofDim[Vector](row, column)
    for (i <- 0 until init.length) {
      for (j <- 0 until init(0).length) {
        val vec = for (h <- 0 until 3) yield Random.nextDouble()
        init(i)(j) = new VectorImpl(vec)
      }
    }
    init
  }

  def this() = this(5, 5)


  def train(teachers : Seq[Vector]) = {
    for ((teacher, i) <- teachers.zipWithIndex) {
      val (row, column, dis) = bestMatchingUnit(teacher)
      val l = learningRatio(i)
      val s = learningRadius(i, dis)
      for (x <- -1 to 1) {
        for (y <- -1 to 1) {
          for (z <- 0 until teacher.size)
            matrix(row+x)(column+y).+((teacher - matrix(row+x)(column+y)) * l * s )
        }
      }
    }

  }

  def bestMatchingUnit (teacher : Vector) : (Int, Int, Double) = {
    var row = 0
    var column = 0
    var distance = Double.PositiveInfinity
    for (i <- 0 until matrix.length) {
      for (j <- 0 until matrix(0).length) {
        if (teacher.distance(matrix(i)(j)) < distance) {
          distance = teacher.distance(matrix(i)(j))
          row = i
          column = j
        }
      }
    }
    (row, column, distance)
  }

  def neighbourhood(t : Int) : Double = {
    val N = 20
    val teacherN = 10000
    val halfLife = (teacherN / 4).asInstanceOf[Double]
    val initial = (N / 2).asInstanceOf[Double]
    initial * math.exp(-t / halfLife)
  }

  def learningRatio(t : Int) : Double = {
    val teacherN = 10000
    val halfLife = (teacherN / 4).asInstanceOf[Double]
    val initial  = 0.1
    initial * math.exp(-t / halfLife)
  }

  def learningRadius(t : Int, dis : Double) : Double = {
    val s = neighbourhood(t)
    math.exp(-math.pow(dis, 2) /(2 * math.pow(s, 2)))
  }
}
