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

  test("test$plus") {
    val vector = vector3+vector4
    assert(vector(0) == 2)
    assert(vector(1) == 3)
    assert(vector(2) == 4)
    assert(vector(3) == 5)
    assert(vector(4) == 6)
  }

  test("test$minus") {
    val vector = vector3-vector4
    assert(vector(0) == 0)
    assert(vector(1) == -1)
    assert(vector(2) == -2)
    assert(vector(3) == -3)
    assert(vector(4) == -4)
  }

  test("test$times") {
    val dot = vector3*vector4
    assert(dot == 15)

  }

  test("test$times$scala") {
    val vector = vector4 * 2
    assert(vector(0) == 2)
    assert(vector(1) == 4)
    assert(vector(2) == 6)
    assert(vector(3) == 8)
    assert(vector(4) == 10)
  }

}
