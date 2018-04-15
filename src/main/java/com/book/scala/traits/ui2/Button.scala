package com.book.scala.traits.ui2

import com.book.scala.traits.ui.Widget

/**
  * 《Scala程序设计》第9章 特征
  * 9.3 可堆叠的特征
  * 由于Button救命中使用某一 protected方法人对点击事件进行代码，所以我们无需为该示例引用 Clickable这个简单接口，
  * 不过考虑到将公共抽象从具体实现细节中分享出来是一个很好的惯用方法，我们不是将它们整合到了一直。
  * 这也是"4人组"设计模式中所描述的模板方法模式的一个简单示例
  *
  * Time 2018-03-17 07:45:21
  * Created by Cao Wei Dong on 2018-03-17.
  */
class Button(val label: String) extends Widget with Clickable {
	override def updateUI(): Unit = {
		/* 修改GUI样式的逻辑代码 */
	}
}
