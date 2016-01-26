package com.github.dyamah.scalasom

/**
 * Created by sakaisawayuya on 2016/01/20.
 */
class VectorEx (vec : Seq[Double])  extends Vector {

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
    /* for の違う形？
    var sum = 0.0
    for (i <- 0 to this.size-1)
      sum += this(i) * that(i)
    sum
     */
  }

  /** ベクトルを scalar 倍したベクトルを返す
    *
    * @param scalar 係数
    * @return scalar 倍したベクトル
    */
  override def *(scalar: Double): Vector = {
    val mulvec  = for (i <- 0 until this.size) yield scalar * this(i)
    new VectorEx(mulvec)
  }

  /** このベクトルとの加算したベクトル返す
    *
    * @param that 加算を計算する対象のベクトル
    * @return 加算した結果のベクトル
    */
  override def +(that: Vector): Vector = {
    if (this.size != that.size) throw new Exception("Their length of the vector is different")
    val addVec = for (i <- 0 until this.size) yield this(i) + that(i)
    new VectorEx(addVec)
  }

  /** このベクトルとの減算したベクトル返す
    *
    * @param that 減算を計算する対象のベクトル
    * @return 減算した結果のベクトル
    */
  override def -(that: Vector): Vector = {
    if (this.size != that.size) throw new Exception("Their length of the vector is different")
    val subVec = for (i <- 0 until this.size) yield this(i) - that(i)
    new VectorEx(subVec)
  }
}
