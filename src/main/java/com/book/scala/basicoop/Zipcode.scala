package com.book.scala.basicoop

/**
  * 《Scala程序设计》第8章：Scala面向对象编程
  * 8.7 验证输入
  * 如果想验证僌的参数，以确保产生的实例处于有效状态，该怎么做呢？、
  *     Predef定义了一系列名为require的重载方法，可以实现这一目的。
  * 下面是一个测试这个功能的Demo：
  *     这是一个封闭了美国邮政编码以的类。邮政编码允许使用两种形式，
  *     5位数字或者邮政编码加4位数字的形式
  *     后者较前者多了结尾的4位数字
  * Created by Cao Wei Dong on 2018-03-16.
  */
case class Zipcode(zip: Int, extension: Option[Int] = None){
	//使用require方法验证输入
	require(valid(zip,extension), s"Invalid Zip+4 specified: $toString")

	protected def valid(z: Int, e: Option[Int]): Boolean = {
		if (0 < z && z <= 99999) e match {
			case None => validUSPS(z, 0)
			case Some(e) => 0 < e && e <= 9999 && validUSPS(z, e)
		} else
			false
	}

	//这是一个有效的美国邮政编码吗？
	protected def validUSPS(i: Int, e: Int): Boolean = true

	//覆写toString方法，返回符合人们预期的邮政编码格式
	//对结尾可能的四位数进行恰当的处理
	override def toString =
		if (extension != None) s"$zip-${extension.get}" else zip.toString
}

object Zipcode{
	def apply(zip: Int, extension: Int): Zipcode =
		new Zipcode(zip, Some(extension))
}
