package com.github.dyamah.scalasom

import scala.util.Random

/**
 * Created by sakaisawayuya on 2016/01/26.
 */
object Sample {
  def main (args: Array[String]){
    /**
    val v1 = new VectorImpl(Seq.fill(5)(2.0))

    val array = Array[Double](1, 2, 3, 4, 5)
    val v2 = new VectorImpl(array)

    val list = List[Double](1, 2, 3, 4, 5)
    val v3 = new VectorImpl(list)

    val v4 = v1 * 2
    val v5 = v1 + v2
    val v6 = v1 - v2

    println(v1 * v2)
    println(v2 * v3)
    println(v4(0))
    println(v1.distance(v1))
    println(v1.distance(v2))
      **/

    val somBulider = new SOMBuilder
    // var にするしかない？　設計の問題？
    var som = somBulider.build()
    for (i <- 0 until 1000) {
      val data = for (j <- 0 until 3) yield Random.nextDouble()
      som = somBulider.train(som, new VectorImpl(data))
    }

    println()
  }
}
