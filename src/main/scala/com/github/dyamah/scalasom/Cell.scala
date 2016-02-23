package com.github.dyamah.scalasom

/**
 * Created by sakaisawayuya on 2016/02/02.
 */
case class Cell(i: Int, j: Int, vector: Vector) {
  // cell 間の距離を定義
  // 前のは cell が持つベクトルの距離を返していてよくわからない挙動に
  def distance(that: Cell) : Double = math.abs(this.i - that.i) + math.abs(this.j - that.j)
}
