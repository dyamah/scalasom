package com.github.dyamah.scalasom

/**
 * Created by sakaisawayuya on 2016/01/19.
 */
trait Vector {

  /** このベクトルの要素数を返す
    */
  def size: Int

  /** i番目の要素の値を返す
    *
    * @param i 添字
    */
  def apply(i: Int): Double

  /** このベクトルと内積値を返す
    *
    * @param that 内積を計算する対象のベクトル
    */
  def *(that : Vector) : Double

  /** ベクトルを scalar 倍したベクトルを返す
    *
    * @param scalar 係数
    * @return scalar 倍したベクトル
    */
  def *(scalar : Double) : Vector

  /** このベクトルとの加算したベクトル返す
    *
    * @param that 加算を計算する対象のベクトル
    * @return 加算した結果のベクトル
    */
  def +(that : Vector) : Vector

  /** このベクトルとの減算したベクトル返す
    *
    * @param that 減算を計算する対象のベクトル
    * @return 減算した結果のベクトル
    */
  def -(that : Vector) : Vector

  /** このベクトルとの距離を返す
    *
    * @param that 距離を計算する対象のベクトル
    * @return 距離
    */
  def distance(that : Vector) : Double
}


