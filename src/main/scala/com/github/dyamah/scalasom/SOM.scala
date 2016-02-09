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
    * @param x 入力ベクトル
    * @param t 時間（学習回数）
    * @param learningRatio t における学習率を返す関数
    * @param radius t における更新半径の値を返す関数
    */
  def train(x: Vector, t: Int, learningRatio: Int => Double, radius: (Int, Double) => Double) : SOM

  /** マップ上で入力ベクトルxと最も類似したベクトルを持つセルを返す
    *
    * @param x
    */
  def bestMatchingCell(x : Vector) : Option[Cell]

}


class SOMImpl (private val cells: Seq[Cell]) extends SOM {

  require(cells.nonEmpty)

  def apply(i: Int, j: Int) : Cell = cells.filter(_.i == i).filter(_.j == j).head

  def iterator : Iterator[Cell] = cells.iterator

  val rows : Int = cells.map(_.i).max

  val columns : Int = cells.map(_.j).max

  /** コンストラクタ
    *
    * @param rows 横のセル数。デフォルトは5
    * @param columns 縦のセル数。デフォルトは5
    * @param initVectorGenerator 座標i,jのセルに対する初期ベクトルを生成する関数。
    */
  def this(rows: Int = 5, columns: Int = 5,
           initVectorGenerator : (Int, Int) => Vector = { (i, j) => new VectorImpl(Seq(0,0,0))} ) = {
    this((for (i <- 0 until rows; j <- 0 until columns) yield Cell(i, j, initVectorGenerator(i, j))).toSeq)
    // 正しいコンストラクタの実装を書く必要がある
  }

  def bestMatchingCell(vector: Vector): Option[Cell] = cells.sortBy { _.vector.distance(vector) }.headOption


  def train(vector : Vector, t: Int, ratio : Int => Double, radius : (Int, Double) => Double): SOMImpl = {

    bestMatchingCell(vector).map {
      bmc =>

        val radius_ = radius(t, vector.distance(bmc.vector))

        new SOMImpl(
          iterator.map {
            cell =>
              if (cell.distance(bmc) < radius_) {
                val u = (vector -  cell.vector) * (radius_ * ratio(t))
                Cell(cell.i, cell.j, cell.vector + u )
              }
              else cell

          }.toSeq)
    }.getOrElse(this)
  }
}