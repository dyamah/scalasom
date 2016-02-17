package com.github.dyamah.scalasom

import scala.util.Random


/**
 * Created by sakaisawayuya on 2016/02/02.
 */
class SOMBuilder {
  /*
  var row: Int = 5
  var column: Int = 5
  var dim: Int = 10
  var ratio: (Int, Int) => Double = defaultRation
  var radius: (Int, Int, Double) => Double = defaultRadius
  var total = 0

  /** 学習パラメータ (L) の計算
    *
    * @param time 学習回数
    * @param total 事例数
    * @return
    */
  private def defaultRation(time: Int, total: Int) : Double = {
    val halfLife: Double = (total / 4)
    val initial  = 0.1
    initial * math.exp(-time / halfLife)
  }

  /** 学習パラメータ (Θ) の計算
    *
    * @param time 学習回数
    * @param total 事例数
    * @param distance BMC との距離
    * @return
    */
  private def defaultRadius(time: Int, total: Int, distance: Double) : Double = {
    /** σ関数の計算（近傍を計算）
      *
      * @return
      */
    def neighbourhood() : Double = {
      val N = this.row
      val halfLife: Double  = (total / 4)
      val initial: Double = (N / 2)
      initial * math.exp(-time / halfLife)
    }
    val s = neighbourhood()
    math.exp(-math.pow(distance, 2) / (2 * math.pow(s, 2)))
  }

  def withRow(r: Int) : SOMBuilder = {
    row = r
    this
  }
  def withColumn(c: Int) : SOMBuilder = {
    column = c
    this
  }
  def withDim(d: Int) : SOMBuilder = {
    dim = d
    this
  }
  def withRatio(rat: (Int, Int) => Double) : SOMBuilder = {
    ratio = rat
    this
  }
  def withRadius(rad: (Int, Int, Double) => Double) : SOMBuilder = {
    radius = rad
    this
  }

  def build(): SOM = {
    val som = for (i <- 0 until row; j <- 0 until column) yield
      Cell(i, j , new VectorImpl(for (h <- 0 until 3) yield Random.nextDouble()))
    new SOM(som)
  }

  def train(som: SOM, vector: Vector): SOM = {
    // total と time は誰に責任を持たせるのが正解？
    val time = 1
    val bestCell = bestMatchingCell(som, vector)
    val ratioValue = ratio(time, total)
    val radiusValue = radius(time, total, bestCell.vector.distance(vector))
    // スマートにかけるのまだ見つかりませんでした
    /*
    val newSom = for {
      cell <- som.matrix
      x <- -1 to 1
      y <- -1 to 1
      if (-1 < bestCell.i+x && bestCell.i+x < row && -1 < bestCell.j+y && bestCell.j+y < column)
    }
    */
    val newSom = for (i <- 0 until row; j <- 0 until column) {

    }



    this.total += 1
    // とりあえず
    new SOM(newSom)
  }

  private def bestMatchingCell (som: SOM, vector: Vector): Cell = {
    val bestCell = som.matrix.sortWith(_.vector.distance(vector) < _.vector.distance(vector)).head
    bestCell
  }
  */
}
