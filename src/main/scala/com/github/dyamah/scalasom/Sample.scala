package com.github.dyamah.scalasom

/**
 * Created by sakaisawayuya on 2016/01/26.
 */
object Sample {
  def main (args: Array[String]){
    val v1 = new VectorEx(Seq.fill(5)(2))
    println(v1.size)
    println(v1(1))

    val array = Array[Double](1, 2, 3, 4, 5)
    val v2 = new VectorEx(array)
    println(v2.size)
    println(v2(3))

    println(v1 * v2)
  }
}
