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

//  /** #### このメソッドの役割がよくわかりませんでした
//   * ベクトルを返す
//   * @return ベクトル
//   */
//  def getVector(): Array[Double];
//
//  /** ### 原則、メソッド名は キャメルケースで http://yanana.github.io/scala-style/index.html
//   * 次元を返す
//   * @return 次元
//   */
//  def Dimension(): Int;
//
//  /**
//   * ベクトルの大きさを返す
//   * @return ベクトルの大きさ
//   */
//  def Size(): Int;
//
//  /** ### scala標準の Listクラスや Vectorクラスを見てみてください。applyメソッドについて理解をする必要もありそうです。
//   * index 次元の値を返す
//   * @param index 次元
//   * @return index 次元の値
//   */
//  def getElement(index: Int): Double;
//
//  /**
//   * index 次元の値に num を加える
//   * @param index インデックス
//   * @param num 加える数
//   */
//  def addElement(index: Int, num: Double);
}


