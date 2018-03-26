package com.book.scala.basicoop

import com.book.scala.basicoop2

/**
  * 《Scala程序设计》第8章：Scala面向对象编程
  * Scala构造器
  *
  * time 2018-03-16 05:23:54
  * Created by Cao Wei Dong on 2018-03-16.
  */
case class Address(street: String, city: String, state: String, zip: String){
	//次级构造器只带一个参数，即邮政编码。内部调用了其他辅助函数，通过邮政编码得到城市名和州名，但无法得到街道名。
	def this(zip: String) =
		this("[unknown", basicoop2.Address.zipToCity(zip), basicoop2.Address.zipToState(zip), zip)
}
//邮政编码查找城市和州的辅助函数
object Address{
	def zipToCity(zip: String) = "Anytown"
	def zipToState(zip: String) = "CA"
}

case class Person(
	 name: String,
	 age:  Option[Int],
	 address: Option[basicoop2.Address]){
	def this(name: String) = this(name, None, None)

	def this(name:String, age: Int)  = this(name, Some(age), None)

	def this(name: String, age: Int, address: basicoop2.Address) =
		this(name, Some(age), Some(address))

	def this(name: String, address: basicoop2.Address) = this(name, None, Some(address))
}


