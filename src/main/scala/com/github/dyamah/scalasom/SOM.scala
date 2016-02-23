package com.github.dyamah.scalasom

/**
 * Created by dyama on 1/19/16.
 **/

trait SOM {

  /** SOM上のセルのイテレーターを返す
    */
  def iterator: Iterator[Cell]

  /** 座標i,j の位置のセルを返す
    *
    * @param i 横の座標
    * @param j 縦の座標
    */
  def apply(i: Int, j: Int) : Cell

  /** マップの横のセル数
    *
    */
  val rows : Int

  /** マップの縦のセル数
    *
    */
  val columns : Int

  /** 学習して、更新があれば新しいSOMを返す
    *
    * @param vector 入力ベクトル
    * @param t 時間（学習回数）
    * @param radius t における更新半径の値を返す関数
    * @param learningRate t における学習率の値を返す関数
    * @param influenceRate 影響率
    */
  def train(vector : Vector, t: Int, radius : Int => Double, learningRate : Int => Double, influenceRate : (Double, Double) => Double) : SOM

  /** マップ上で入力ベクトルxと最も類似したベクトルを持つセルを返す
    *
    * @param x
    */
  def bestMatchingCell(x : Vector) : Option[Cell]

}


class SOMImpl (private val cells: Seq[Cell]) extends SOM {

  require(cells.nonEmpty)

  def apply(i: Int, j: Int) : Cell = cells.filter(cell => cell.i == i && cell.j == j).head

  def iterator : Iterator[Cell] = cells.iterator

  val rows : Int = cells.map(_.i).max

  val columns : Int = cells.map(_.j).max

  /** コンストラクタ
    *
    * @param rows 横のセル数。デフォルトは5
    * @param columns 縦のセル数。デフォルトは5
    * @param initVectorGenerator 座標i,jのセルに対する初期ベクトルを生成する関数
    */
  // initVectorGenerator は引数を取らない形に変更
  def this(rows: Int = 5, columns: Int = 5,
           initVectorGenerator : () => Vector = { () => new VectorImpl(Seq(0,0,0))} ) = {
    this((for (i <- 0 until rows; j <- 0 until columns) yield Cell(i, j, initVectorGenerator())).toSeq)
  }

  def bestMatchingCell(vector: Vector): Option[Cell] = cells.sortBy { _.vector.distance(vector) }.headOption

  // 学習率の関数などの形を訂正（http://www.saedsayad.com/clustering_som.htm を参考に）
  def train(vector : Vector, t: Int, radius : Int => Double, learningRate : Int => Double, influenceRate : (Double, Double) => Double): SOMImpl = {

    bestMatchingCell(vector).map {
      bmc =>

        val radius_ = radius(t)
        val distance = vector.distance(bmc.vector)

        new SOMImpl(
          iterator.map {
            cell =>
              if (cell.distance(bmc) < radius_) {
                val u = (vector -  cell.vector) * (learningRate(t) * influenceRate(distance, radius_))
                Cell(cell.i, cell.j, cell.vector + u )
              }
              else cell

          }.toSeq)
    }.getOrElse(this)
  }
}