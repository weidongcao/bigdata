package bigdata.scala

/**
  * Created by Administrator on 2016/11/13.
  * 北风网，中华石杉
  * 124_Scala编程进阶：提取器实战详解
  */
object UnapplyTest {
  def main(args: Array[String]): Unit = {
    val dong = Person("dong", 26)
    val Person(name, age) = dong
    println("Persion => " + name + "\t" + age)
  }
  class Person(val name: String, val age: Int)
  object Person{
    def apply(name: String, age: Int): Person = new Person(name, age)

    def unapply(arg: Person): Option[(String, Int)] ={
      Some((arg.name, arg.age))
    }
  }
}
