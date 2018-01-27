package com.bigdata.spark

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 2016/11/12.
  */
object InnerClass {
  def main(args: Array[String]): Unit = {
    val c1 = new Class
    val leo = c1.register("Leo")

    c1.students += leo

    val c2 = new Class
    val jack = c2.register("jack")
    c1.students += jack
    println(c1.students)
  }
}
object Class{
class Student(val name:String)
}
class Class{
  val students = new ArrayBuffer[Class.Student]()
  def register(name: String) = {
    new Class.Student(name)
  }
}

