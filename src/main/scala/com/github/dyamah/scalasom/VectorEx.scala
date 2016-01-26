package com.github.dyamah.scalasom

/**
 * Created by sakaisawayuya on 2016/01/20.
 */
class VectorEx (vec : Seq[Double])  extends Vector {

  require(vec != None)
  val vector = vec
  def this(size: Int, value: Double) = this(Seq.fill(size)(value))

  /** このベクトルの要素数を返す
    */
  override def size: Int = vector.length

  /** i番目の要素の値を返す
    *
    * @param i 添字
    */
  override def apply(i: Int): Double = vector(i)

  /** このベクトルと内積値を返す
    *
    * @param that 内積を計算する対象のベクトル
    */
  override def *(that: Vector): Double = {
    if (this.size != that.size) throw new Exception("Their length of the vector is different")
    val inner = for (i <- 0 until this.size) yield this(i) * that(i)
    inner.sum
  }
}
