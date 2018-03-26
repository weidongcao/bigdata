package com.book.scala.basicoop

import com.book.scala.basicoop2

/**
  * 《Scala程序设计》第8章：Scala面向对象编程
  * Scala构造器
  * 在伴随对象中重载Person.apply，就可以得到使得的"构造器"，避免使用new
  * Time：2018-03-16 05:42:38
  * Created by Cao Wei Dong on 2018-03-16.
  */
case class Person3(
	  name: String,
	  age: Option[Int] = None,
	  address: Option[basicoop2.Address] = None
)

object Person3{
	def apply(name: String): Person3 = new Person3(name)

	def apply(name: String, age: Int): Person3 = new Person3(name, Some(age))

	def apply(name: String, age: Int, address: basicoop2.Address):Person3 = new Person3(name, Some(age), Some(address))

	def apply(name: String, address: basicoop2.Address): Person3 = new Person3(name, address = Some(address))


}
