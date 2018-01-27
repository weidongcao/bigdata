package com.bigdata.spark

import java.awt.Font
import java.util

/**
  * Created by Administrator on 2016/11/12.
  */
object ImplicitMap {
  def main(args: Array[String]): Unit = {
    val javaScore = new util.HashMap[String, Int]()

    javaScore.put("Alice", 10)
    javaScore.put("dong", 30)
    javaScore.put("carmon", 50)

    import scala.collection.JavaConversions.mapAsScalaMap
    val scalaScore : scala.collection.mutable.Map[String, Int] = javaScore

    println("scalaScore => " + scalaScore)

    import java.awt.font.TextAttribute._

    import scala.collection.JavaConversions.mapAsJavaMap

    val scalaAttrMap = Map(FAMILY -> "Serif", SIZE -> 12)
    val font = new Font(scalaAttrMap)

    println(font)
  }
}
