package com.github.dyamah.scalasom

import org.scalatest.FunSuite

/**
 * Created by sakaisawayuya on 2016/02/23.
 */
class SOMImplTest extends FunSuite {


  def initVectorGenerator () : Vector = {
    new VectorImpl(for (h <- 0 until 3) yield 1.0)
  }

  val som = SOMImpl(3, 3, initVectorGenerator)
  val som2 = SOMImpl(2, 4, initVectorGenerator)

  // ファクトリメソッドによって全てのセルが同じベクトルを持つため全てセル列の最初が bmc で出てくる
  test("testBestMatchingCell") {
    assert(som.bestMatchingCell(new VectorImpl(List(0.5, 0.5, 0.5))).get == Cell(1, 1, initVectorGenerator()))
    assert(som.bestMatchingCell(new VectorImpl(List(1, 1, 1))).get == Cell(1, 1, initVectorGenerator()))
    assert(som.bestMatchingCell(new VectorImpl(List(2, 2, 2))).get == Cell(1, 1, initVectorGenerator()))
  }

  test("testRows") {
    assert(som.rows == 3)
    assert(som2.rows == 2)
  }


  test("testColumns") {
    assert(som.columns == 3)
    assert(som2.columns == 4)
  }

  // 自分で作ったデフォルトの 3×3 のSOM と入力に対して、正しい出力が返ってきているかテスト
  test("testTrain") {
    def influenceRate(distance: Double, radius: Double) : Double = {1.0}
    def radius(time: Int) : Double = {1.0}
    val somTrain_1 = som.train(new VectorImpl(List(2, 2, 2)), 1, radius, influenceRate)
    val iterator1 = somTrain_1.iterator
    assert(iterator1.next() == Cell(1, 1, new VectorImpl(List(1.1, 1.1, 1.1))))
    assert(iterator1.next() == Cell(1, 2, new VectorImpl(List(1.1, 1.1, 1.1))))
    assert(iterator1.next() == Cell(1, 3, initVectorGenerator()))
    assert(iterator1.next() == Cell(2, 1, new VectorImpl(List(1.1, 1.1, 1.1))))
    assert(iterator1.next() == Cell(2, 2, initVectorGenerator()))
    assert(iterator1.next() == Cell(2, 3, initVectorGenerator()))
    assert(iterator1.next() == Cell(3, 1, initVectorGenerator()))
    assert(iterator1.next() == Cell(3, 2, initVectorGenerator()))
    assert(iterator1.next() == Cell(3, 3, initVectorGenerator()))

    
    val somTrain_2 = somTrain_1.train(new VectorImpl(List(0.5, 0.5, 0.5)), 2, radius, influenceRate)
    val iterator2 = somTrain_2.iterator
    assert(iterator2.next() == Cell(1, 1, new VectorImpl(List(1.1, 1.1, 1.1))))
    assert(iterator2.next() == Cell(1, 2, new VectorImpl(List(1.04, 1.04, 1.04))))
    assert(iterator2.next() == Cell(1, 3, new VectorImpl(List(0.95, 0.95, 0.95))))
    assert(iterator2.next() == Cell(2, 1, new VectorImpl(List(1.1, 1.1, 1.1))))
    assert(iterator2.next() == Cell(2, 2, initVectorGenerator()))
    assert(iterator2.next() == Cell(2, 3, new VectorImpl(List(0.95, 0.95, 0.95))))
    assert(iterator2.next() == Cell(3, 1, initVectorGenerator()))
    assert(iterator2.next() == Cell(3, 2, initVectorGenerator()))
    assert(iterator2.next() == Cell(3, 3, initVectorGenerator()))
  }
  
  // セル列をコンストラクタで受け取らないことで順番はこちらで制御。
  // 行順で出てくるように設計。
  test("testIterator") {
    val iterator = som.iterator
    assert(iterator.next() == Cell(1, 1, initVectorGenerator()))
    assert(iterator.next() == Cell(1, 2, initVectorGenerator()))
    assert(iterator.next() == Cell(1, 3, initVectorGenerator()))
    assert(iterator.next() == Cell(2, 1, initVectorGenerator()))
    assert(iterator.next() == Cell(2, 2, initVectorGenerator()))
    assert(iterator.next() == Cell(2, 3, initVectorGenerator()))
    assert(iterator.next() == Cell(3, 1, initVectorGenerator()))
    assert(iterator.next() == Cell(3, 2, initVectorGenerator()))
    assert(iterator.next() == Cell(3, 3, initVectorGenerator()))
    assert(iterator.hasNext == false)

    val iterator2 = som2.iterator
    assert(iterator2.next() == Cell(1, 1, initVectorGenerator()))
    assert(iterator2.next() == Cell(1, 2, initVectorGenerator()))
    assert(iterator2.next() == Cell(1, 3, initVectorGenerator()))
    assert(iterator2.next() == Cell(1, 4, initVectorGenerator()))
    assert(iterator2.next() == Cell(2, 1, initVectorGenerator()))
    assert(iterator2.next() == Cell(2, 2, initVectorGenerator()))
    assert(iterator2.next() == Cell(2, 3, initVectorGenerator()))
    assert(iterator2.next() == Cell(2, 4, initVectorGenerator()))
    assert(iterator2.hasNext == false)
  }

}
