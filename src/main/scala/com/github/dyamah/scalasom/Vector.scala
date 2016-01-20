package com.github.dyamah.scalasom

/**
 * Created by sakaisawayuya on 2016/01/19.
 */
trait Vector {

  /**
   * ベクトルを返す
   * @return ベクトル
   */
  def getVector(): Array[Double];

  /**
   * 次元を返す
   * @return 次元
   */
  def Dimension(): Int;

  /**
   * ベクトルの大きさを返す
   * @return ベクトルの大きさ
   */
  def Size(): Int;

  /**
   * index 次元の値を返す
   * @param index 次元
   * @return index 次元の値
   */
  def getElement(index: Int): Double;

  /**
   * index 次元の値に num を加える
   * @param index インデックス
   * @param num 加える数
   */
  def addElement(index: Int, num: Double);
}
