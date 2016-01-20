package com.github.dyamah.scalasom

/**
 * Created by sakaisawayuya on 2016/01/20.
 */
class VectorEx (dim: Int, value: Double, _vector: Array[Double])  extends Vector {

  val vector = new Array[Double](dim)

  def this(dim: Int) = this(dim, null, null)
  def this(dim: Int, value: Double) = this(dim, value, null)
  def this(_vector: Array[Double]) = this(null, null, _vector)

  /**
   * ベクトルを返す
   * @return ベクトル
   */
  override def getVector(): Array[Double] = ???

  /**
   * 次元を返す
   * @return 次元
   */
  override def Dimension(): Int = ???

  /**
   * index 次元の値を返す
   * @param index 次元
   * @return index 次元の値
   */
  override def getElement(index: Int): Double = ???

  /**
   * index 次元の値に num を加える
   * @param index インデックス
   * @param num 加える数
   */
  override def addElement(index: Int, num: Double): Unit = ???

  /**
   * ベクトルの大きさを返す
   * @return ベクトルの大きさ
   */
  override def Size(): Int = ???
}
