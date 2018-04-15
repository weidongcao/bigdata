package com.book.scala.traits.ui

import com.book.scala.traits.observer.Subject

/**
  * 《Scala程序设计》第9章特征
  * 9.2 混入trait
  * ObservableButton类继承了Button类，并混入了Subject特征。
  * 该类使用Button类型作为Subject特征的类型参数，在Subject特征声明中，
  * 该类型参数名为state
  *
  * Time 2018-03-17 06:38:12
  * Created by Cao Wei Dong on 2018-03-17.
  */
class ObservableButton(name: String) extends Button(name) with Subject[Button]{
	//为了能够通知观察者，我们需要覆写click方法。假如存在其他受状态变化影响的方法，我们同样需要覆写这些方法。
	override def click(): Unit = {
		//首先调用父类的click()
		super.click()
		//通知观察者们：将this对象作为state传递给notifyObservers方法。d当前场景中，除了按钮点击事件之外，不会发生其他事件
		notifyObservers(this)
	}

}
