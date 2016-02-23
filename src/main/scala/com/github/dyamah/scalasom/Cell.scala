package com.github.dyamah.scalasom

/**
 * Created by sakaisawayuya on 2016/02/02.
 */
case class Cell(i: Int, j: Int, vector: Vector) {

  // Add and multiply by prime numbers
  override def hashCode = (i + 31) * 31 + j

  override def equals(other:Any) = other match {
    case that: Cell =>
      (that canEqual this) && (this.i == that.i) && (this.j == that.j) && (this.vector == that.vector)
    case _ => false
  }
  // Pointを継承した他のクラスのインスタンスでないかチェック
  def canEqual(other:Any) = other.isInstanceOf[Cell]

  // cell 間の距離を定義
  // 前のは cell が持つベクトルの距離を返していてよくわからない挙動に
  def distance(that: Cell) : Double = math.pow(math.pow(this.i - that.i, 2)+math.pow(this.j - that.j, 2), 0.5)
}
