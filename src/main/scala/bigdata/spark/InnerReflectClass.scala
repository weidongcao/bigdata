package bigdata.scala

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 2016/11/12.
  */
object InnerReflectClass {
  def main(args: Array[String]): Unit = {
    val file = "E:\\Workspaces\\resource\\TestData\\logs.txt"
    val c1 = new Class
    val leo = c1.register("Leo")
    c1.students += leo

    val c2 = new Class
    val jack = c2.register("jack")
    c1.students += jack

    println(c1.students)

    val c3 = new Class1("c3")
    val carmon = c3.register("carmon")
    println(carmon.introduceMySelf)

  }
  class Class{
    class Student(name: String)
    val students = new ArrayBuffer[Class#Student]
    def register(name:String) = {
      new Student(name)
    }
  }

  class Class1(name: String){outer =>
    class Student(name: String){
      def introduceMySelf = "Hello, I'm " + name + " ,I'm very happy to join class" + outer.name
    }
    def register(name:String) = {
      new Student(name)
    }
  }
}
