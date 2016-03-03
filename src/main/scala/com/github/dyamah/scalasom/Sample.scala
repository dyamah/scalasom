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
    def influenceRate(distance: Double, radius: Double) : Double = {
      math.exp(-math.pow(distance, 2) / (2*math.pow(radius, 2)))
    }
    def radius(time: Int) : Double = {
      val init = 2
      val total = 1000.0
      init*math.exp(-time/total)
    }

    val data = (1 to 1000).map {i => (initVectorGenerator(), i)}
    val initSom = new SOMImpl(10, 10, initVectorGenerator)
    val som = data.foldLeft(initSom)((z, d) => z.train(d._1, d._2, radius, influenceRate))
    //val som = data.foldLeft(new SOMImpl(10, 10, initVectorGenerator))((z, d) => z.train(d._1, d._2, radius, influenceRate))
    println(som.rows)
    println(som.columns)
  }
}
