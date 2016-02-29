package com.github.dyamah.scalasom

import org.scalatest.FunSuite

/**
 * Created by sakaisawayuya on 2016/02/29.
 */
class CellTest extends FunSuite {

  val cell1 = Cell(1, 1, new VectorImpl(List(1, 1, 1)))
  val cell2 = Cell(1, 1, new VectorImpl(List(1, 1, 1)))
  val cell3 = Cell(1, 1, new VectorImpl(List(2, 2, 2)))
  val cell4 = Cell(1, 2, new VectorImpl(List(1, 1, 1)))
  val cell5 = Cell(2, 1, new VectorImpl(List(1, 1, 1)))
  val cell6 = Cell(2, 2, new VectorImpl(List(1, 1, 1)))

  test("testEquals") {
    assert(cell1 == cell2)
    assert(cell1 != cell3)
    assert(cell1 != cell4)
    assert(cell1 != cell5)
    assert(cell1 != cell6)
  }

  test("testDistance") {
    assert(cell1.distance(cell1) == 0)
    assert(cell1.distance(cell2) == 0)
    assert(cell1.distance(cell3) == 0)
    assert(cell1.distance(cell4) == 1)
    assert(cell1.distance(cell5) == 1)
    assert(cell1.distance(cell6) == math.pow(2, 0.5))
  }

}
