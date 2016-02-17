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
    def initVectorGenerator (i: Int, j: Int) : Vector = {
      new VectorImpl(for (h <- 0 until 3) yield Random.nextDouble())
    }
    def ratio(time: Int) : Double = {
      val init = 0.1
      val total = 250.0
      init*math.exp(-time/total)
    }
    def radius(time: Int, distance: Double) : Double = {
      def sigmoid(): Double = 1.0 / (1 + math.exp(-time))
      math.exp(-math.pow(distance, 2) / (2*math.pow(sigmoid(), 2)))
    }

    var som = new SOMImpl(5, 5, initVectorGenerator)
    for (i <- 0 until 1000) {
      som = som.train(initVectorGenerator(3,3), i, ratio, radius)
    }
    println(som.rows)
    println(som.columns)
  }
}
