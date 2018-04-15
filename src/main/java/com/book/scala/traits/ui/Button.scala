package com.book.scala.traits.ui

/**
  * 《Scala程序设计》第9章 特征
  * 9.2 混入trait
  *
  * Time：2018-03-17 06:09:18
  * Created by Cao Wei Dong on 2018-03-17.
  */
class Button(val label: String) {
	def click(): Unit=updateUI()

	def updateUI(): Unit={
		/* 本方法包含了GUI样式的修改逻辑 */
	}

}
