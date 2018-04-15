package com.book.scala.traits.ui2

import com.book.scala.traits.observer.Subject

/**
  * 《Scala程序设计》第9章：特征
  * 9.3 可堆叠的特征
  * Observation特征应该依附于Clickable特征，而不再是之前的Button类。假如按照这种方式对代码进行重构，
  * 我们就不需要再关心如何观察按钮事件。但是由于我们确实需要观察像点击这样的事件，为此我们引入了仅用于观察Clickable事件的trait
  *
  * 事实上，当我们将该trait混入到像Button定义了Click方法的具体实例中时，super便绑定了该实例。abstract关键字提醒编译器：
  *     尽管ObservableClicks.click方法提供了方法体，但click方法并没有完全实现。
  *
  * Time：2018-03-17 07:50:33
  * Created by Cao Wei Dong on 2018-03-17.
  */
trait ObservableClicks extends Clickable with Subject[Clickable]{
	abstract override def click(): Unit = {
		super.click()
		notifyObservers(this)
	}
}
