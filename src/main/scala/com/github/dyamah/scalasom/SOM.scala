package com.github.dyamah.scalasom

import scala.util.Random

/**
  * Created by dyama on 1/19/16.
  */
class SOM (private val row : Int, private val column : Int) {

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

  // train で SOM を返すのはおかしい？ SOM に教師を与えたらその SOM が学習してほしい？

  /** SOM を学習する
    *
    * @param teachers 学習事例（全事例）
    * @return 学習された SOM
    */
  def train(teachers : Seq[Vector]): Unit = {
    val teacherN = teachers.length
    for ((teacher, i) <- teachers.zipWithIndex) {
      train(teacher, i, teacherN)
    }
  }

  /** 1 事例毎の学習
    *
    * @param teacher 学習事例（1 事例）
    * @param number 学習回数
    * @return 学習された SOM （途中結果）
    */
  def train(teacher : Vector, number : Int, teacherN : Int) : Unit = {
    val (bmuRow, bmuColumn, dis) = bestMatchingUnit(teacher)
    val l = learningRatio(number, teacherN)
    val s = learningRadius(number, teacherN, dis)
    println(bmuRow, bmuColumn, dis)
    println()
    for (x <- -1 to 1)
      for (y <- -1 to 1)
        if (-1 < bmuRow+x && bmuRow+x < row && -1 < bmuColumn+y && bmuColumn+y < column)
          matrix(bmuRow+x)(bmuColumn+y) = matrix(bmuRow+x)(bmuColumn+y) + ((teacher - matrix(bmuRow+x)(bmuColumn+y)) * l * s)
    println()
  }

  /** 現在も matrix の中で学習事例と一番近いユニットを探す
    *
    * @param teacher 学習事例
    * @return ユニットの位置とそのユニットと学習事例のユークリッド距離
    */
  private def bestMatchingUnit (teacher : Vector) : (Int, Int, Double) = {
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

  /** σ関数の計算（近傍を計算）
    *
    * @param t 学習回数
    * @return
    */
  private def neighbourhood(t : Int, teacherN : Int) : Double = {
    val N = this.row
    val halfLife = (teacherN / 4).asInstanceOf[Double]
    val initial = (N / 2).asInstanceOf[Double]
    initial * math.exp(-t / halfLife)
  }

  /** 学習パラメータ (L) の計算
    *
    * @param t 学習回数
    * @return
    */
  private def learningRatio(t : Int, teacherN : Int) : Double = {
    val halfLife = (teacherN / 4).asInstanceOf[Double]
    val initial  = 0.1
    initial * math.exp(-t / halfLife)
  }

  /** 学習パラメータ (Θ) の計算
    *
    * @param t 学習回数
    * @param dis BMU の距離
    * @return
    */
  private def learningRadius(t : Int, teacherN : Int, dis : Double) : Double = {
    val s = neighbourhood(t, teacherN)
    math.exp(-math.pow(dis, 2) /(2 * math.pow(s, 2)))
  }
}
