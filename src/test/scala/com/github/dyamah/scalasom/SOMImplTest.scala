package com.github.dyamah.scalasom

import org.scalatest.FunSuite

/**
 * Created by sakaisawayuya on 2016/02/23.
 */
class SOMImplTest extends FunSuite {

  val cell1 = Cell(1, 1, new VectorImpl(List(1, 1, 1)))
  val cell2 = Cell(1, 2, new VectorImpl(List(2, 2, 2)))
  val cell3 = Cell(1, 3, new VectorImpl(List(3, 3, 3)))
  val cell4 = Cell(2, 1, new VectorImpl(List(4, 4, 4)))
  val cell5 = Cell(2, 2, new VectorImpl(List(5, 5, 5)))
  val cell6 = Cell(2, 3, new VectorImpl(List(6, 6, 6)))
  val cell7 = Cell(3, 1, new VectorImpl(List(7, 7, 7)))
  val cell8 = Cell(3, 2, new VectorImpl(List(8, 8, 8)))
  val cell9 = Cell(3, 3, new VectorImpl(List(9, 9, 9)))

  val cells = List(cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9)
  val som = new SOMImpl(cells)

  val cells2 = List(cell1, cell2, cell3, cell4, cell5, cell6)
  val som2 = new SOMImpl(cells2)

  test("testBestMatchingCell") {
    assert(som.bestMatchingCell(new VectorImpl(List(8.5, 8.5, 8.5))) == cell8)
    assert(som.bestMatchingCell(new VectorImpl(List(9, 9, 9))).get == cell9)
  }

  test("testColumns") {
    assert(som.columns == 3)
    assert(som2.columns == 3)
  }

  test("testRows") {
    assert(som.rows == 3)
    assert(som2.rows == 2)
  }

  test("testTrain") {
    def influenceRate(distance: Double, radius: Double) : Double = {1.0}
    def radius(time: Int) : Double = {1.0}
    val somTrain_1 = som.train(new VectorImpl(List(2, 2, 2)), 1, radius, influenceRate)
    val iterator1 = somTrain_1.iterator
    assert(iterator1.next() == Cell(1, 1, new VectorImpl(List(1.1, 1.1, 1.1))))
    assert(iterator1.next() == cell2)
    assert(iterator1.next() == Cell(1, 3, new VectorImpl(List(2.9, 2.9, 2.9))))
    assert(iterator1.next() == cell4)
    assert(iterator1.next() == Cell(2, 2, new VectorImpl(List(4.7, 4.7, 4.7))))
    assert(iterator1.next() == cell6)
    assert(iterator1.next() == cell7)
    assert(iterator1.next() == cell8)
    assert(iterator1.next() == cell9)

    val somTrain_2 = somTrain_1.train(new VectorImpl(List(8, 8, 8)), 1, radius, influenceRate)
    val iterator2 = somTrain_2.iterator
    assert(iterator2.next() == Cell(1, 1, new VectorImpl(List(1.1, 1.1, 1.1))))
    assert(iterator2.next() == cell2)
    assert(iterator2.next() == Cell(1, 3, new VectorImpl(List(2.9, 2.9, 2.9))))
    assert(iterator2.next() == cell4)
    assert(iterator2.next() == Cell(2, 2, new VectorImpl(List(5.03, 5.03, 5.03))))
    assert(iterator2.next() == cell6)
    assert(iterator2.next() == Cell(3, 1, new VectorImpl(List(7.1, 7.1, 7.1))))
    assert(iterator2.next() == cell8)
    assert(iterator2.next() == Cell(3, 3, new VectorImpl(List(8.9, 8.9, 8.9))))
  }

  test("testIterator") {
    val iterator = som.iterator
    assert(iterator.next() == cell1)
    assert(iterator.next() == cell2)
    assert(iterator.next() == cell3)
    assert(iterator.next() == cell4)
    assert(iterator.next() == cell5)
    assert(iterator.next() == cell6)
    assert(iterator.next() == cell7)
    assert(iterator.next() == cell8)
    assert(iterator.next() == cell9)
    assert(iterator.hasNext == false)
  }

}
