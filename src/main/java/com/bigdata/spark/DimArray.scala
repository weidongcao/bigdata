package com.bigdata.spark

import scala.collection.JavaConversions.bufferAsJavaList
import scala.collection.mutable.ArrayBuffer

/**
  * 北风网，中华石杉，将Scala数组隐匿转换为Java数组
  */
object DimArray {
  def main(args: Array[String]): Unit = {
    val command = ArrayBuffer("javac", "E:\\Workspaces\\idea\\spark1.6.2\\src\\main\\java\\com\\don\\com.bigdata\\com.j2se\\TestInt.java")

    val processBuilder = new ProcessBuilder(command)

    val process = processBuilder.start()

    val res = process.waitFor()

    import scala.collection.JavaConversions.asScalaBuffer
    import scala.collection.mutable.Buffer

    val cmd:Buffer[String] = processBuilder.command()

  }
}
