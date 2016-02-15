package com.github.dyamah.scalasom

/**
 * Created by sakaisawayuya on 2016/02/02.
 */
case class Cell(i: Int, j: Int, vector: Vector) {
  def distance(that: Cell) : Double = this.vector.distance(that.vector)
}
