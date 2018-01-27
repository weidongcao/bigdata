package com.bigdata.spark

/**
  * Created by Administrator on 2016/11/5.
  */
object CollectionTest {
  def main(args: Array[String]): Unit = {
    val site = "poem" :: ("carmon" :: ("dong" :: Nil))

    for (e <- site) {
      println(e)
    }
    println(site)
  }

}
