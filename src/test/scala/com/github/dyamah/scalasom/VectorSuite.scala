package com.github.dyamah.scalasom

import org.scalatest.FunSuite

/**
 * Created by sakaisawayuya on 2016/01/20.
 */
class VectorSuite extends FunSuite {

  val vector1 = new VectorEx(3, 1)
  val vector2 = new VectorEx(Array(1.0, 2.0, 3.0, 4.0))
  val vector3 = new VectorEx(5, 1)


  test("testDimension") {
    assert(vector1.Dimension() == 3)
    assert(vector2.Dimension() == 4)
    assert(vector3.Dimension() == 5)
  }

  test("testGetElement") {
    assert(vector1.getElement(0) == 1)
    assert(vector1.getElement(1) == 1)
    assert(vector1.getElement(2) == 1)

    assert(vector2.getElement(0) == 1)
    assert(vector2.getElement(1) == 2)
    assert(vector2.getElement(2) == 3)
    assert(vector2.getElement(3) == 4)

    assert(vector3.getElement(0) == 1)
    assert(vector3.getElement(1) == 1)
    assert(vector3.getElement(2) == 1)
    assert(vector3.getElement(3) == 1)
    assert(vector3.getElement(4) == 1)

  }

  test("testAddElement") {
    vector1.addElement(0, 1)
    vector1.addElement(0, 2)
    vector1.addElement(0, 3)

    assert(vector1.getElement(0) == 2)
    assert(vector1.getElement(1) == 3)
    assert(vector1.getElement(2) == 4)
  }

  test("testSize") {
    assert(vector1.Size() == Math.sqrt(3))
    assert(vector2.Size() == Math.sqrt(30))
    assert(vector3.Size() == Math.sqrt(5))
  }

}
