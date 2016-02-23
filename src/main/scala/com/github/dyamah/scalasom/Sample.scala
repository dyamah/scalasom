package com.github.dyamah.scalasom

import scala.util.Random

/**
 * Created by sakaisawayuya on 2016/01/26.
 */
object Sample {
  def main (args: Array[String]){
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
      init*math.exp(-time/total)
    }

    val data = for (i <- 0 until 1000) yield initVectorGenerator()
    val som = data.zipWithIndex.foldLeft(new SOMImpl(10, 10, initVectorGenerator))((z, d) => z.train(d._1, d._2+1, radius, learningRate, influenceRate))
    println(som.rows)
    println(som.columns)
  }
}
