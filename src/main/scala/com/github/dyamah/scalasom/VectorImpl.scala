package com.github.dyamah.scalasom

class VectorImpl private (private val vector : Array[Double])  extends Vector {

  def this(seq: Seq[Double]){
    this({
      val array = new Array[Double](seq.size)
      seq.copyToArray(array)
      seq.toArray
    })
  }

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

    vector.indices.foldLeft(0.0){(dot, i) => dot + vector(i) * that(i)} // ちょっとおそいかもしれないけど
  }

  /** ベクトルを scalar 倍したベクトルを返す
    *
    * @param scalar 係数
    * @return scalar 倍したベクトル
    */
  override def *(scalar: Double): Vector = {
    new VectorImpl(vector.map { _ * scalar })
  }

  /** このベクトルとの加算したベクトル返す
    *
    * @param that 加算を計算する対象のベクトル
    * @return 加算した結果のベクトル
    */
  override def +(that: Vector): Vector = {
    if (this.size != that.size) throw new Exception("Their length of the vector is different")
    new VectorImpl(vector.zipWithIndex.map { case (v, i) => that(i) + v})

  }

  /** このベクトルとの減算したベクトル返す
    *
    * @param that 減算を計算する対象のベクトル
    * @return 減算した結果のベクトル
    */
  override def -(that: Vector): Vector = {
    if (this.size != that.size) throw new Exception("Their length of the vector is different")
    val subVec = for (i <- 0 until this.size) yield this(i) - that(i)
    new VectorImpl(subVec)
  }

  /** このベクトルとの距離を返す
    *
    * @param that 距離を計算する対象のベクトル
    * @return 距離
    */
  override def distance(that: Vector): Double = {
    if (this.size != that.size) throw new Exception("Their length of the vector is different")
    val sub = vector.zipWithIndex.map { case (v, i) => that(i) - v}
    val mul = sub.map {math.pow(_, 2)}
    math.pow(mul.sum, 0.5)
  }
}
