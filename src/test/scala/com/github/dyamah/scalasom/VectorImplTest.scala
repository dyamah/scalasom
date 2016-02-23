package com.github.dyamah.scalasom

import org.scalatest.FunSuite

/**
 * Created by sakaisawayuya on 2016/02/23.
 */
class VectorImplTest extends FunSuite {

  val vector1 = new VectorImpl(List(1, 3, 5))
  val vector2 = new VectorImpl(List(3, 1, 4, 3.14))
  val vector3 = new VectorImpl(Seq(1, 1, 1, 1, 1))
  val vector4 = new VectorImpl(Seq(1, 2, 3, 4, 5))


  test("testDistance") {
    assert(vector1.distance(vector1) == 0)
    assert(vector3.distance(vector4) == math.pow(30, 0.5))
  }


  test("testSize") {
    assert(vector1.size == 3)
    assert(vector2.size == 4)
    assert(vector3.size == 5)
    assert(vector4.size == 5)
  }
  /*
  test("test$plus") {

  }

  test("testApply") {

  }

  test("test$minus") {

  }

  test("test$times") {

  }

  test("test$times") {

  }
  */
}
