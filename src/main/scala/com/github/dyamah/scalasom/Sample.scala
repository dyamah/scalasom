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
    def initVectorGenerator () : Vector = {
      new VectorImpl(for (h <- 0 until 3) yield Random.nextDouble())
    }
    def learningRate(time: Int) : Double = {
      val init = 0.1
      val total = 1000.0
      init*math.exp(-time/total)
    }
    def influenceRate(distance: Double, radius: Double) : Double = {
      math.exp(-math.pow(distance, 2) / (2*math.pow(radius, 2)))
    }
    def radius(time: Int) : Double = {
      val init = 2
      val total = 1000.0
      math.ceil(init*math.exp(-time/total))
    }

    val data = for (i <- 0 until 1000) yield initVectorGenerator()
    val som = data.zipWithIndex.foldLeft(new SOMImpl(10, 10, initVectorGenerator))((z, d) => z.train(d._1, d._2+1, radius, learningRate, influenceRate))
    println(som.rows)
    println(som.columns)
  }
}
