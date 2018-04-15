package com.book.scala.traits.ui2

/**
  * 《Scala程序设计》第9章：特征
  * 9.3可堆叠特征
  * JavaBean规范提出了"可否决"事件的思想，这一思想使得JavaBean状态变化的监听者能否决这一变更。
  * 我们可以应用trait实现相类似的功能："否决"连续点击事件。你可以把该功能想象成阻止用户
  * 连续误点击某一按钮而触发某一金融交易。
  *
  * Time：2018-03-18 05:14:07
  * Author： Weidong Cao
  * Created by Cao Wei Dong on 2018-03-18.
  */
trait VetoableClicks extends Clickable{
	//默认的点击数
	val maxAllowed = 1
	private var count = 0

	abstract override def click() = {
		if(count < maxAllowed){
			count +=1
			super.click()
		}
	}

}
