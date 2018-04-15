package com.book.scala.traits.ui2

/**
  * 《Scala程序设计》第9章 特征
  * 9.3 可堆叠特征
  *
  * Time： 2018-03-17 07:36:14
  * Created by Cao Wei Dong on 2018-03-17.
  */
trait Clickable {
	//click方法是public 方法，此处定义了该方法的具体实现，updateUI方法将代理该方法
	def click(): Unit = updateUI()

	//updateUI方法既是protected方法，也是抽象方法。
	// clickable特征的实现类将负责提供适当的实现逻辑
	def updateUI(): Unit
}
