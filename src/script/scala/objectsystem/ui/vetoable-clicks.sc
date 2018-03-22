/**
  * 《Scala程序设计》第11章
  * 11.4 覆写trait字段与具体字段
  *
  * Time: 2018-03-21 05:56:51
  * Author: Weidong Cao
  */

import com.book.scala.traits.observer.Observer
import com.book.scala.traits.ui.Button
import com.book.scala.traits.ui2.{Clickable, ObservableClicks, VetoableClicks}

//通过混入所需trait，我们构造了一个可观察的(observable)按钮
val observableButton = new Button("Dong") with ObservableClicks with VetoableClicks{
	//覆写val变量是我们此次练习的主要目的。请留意，此处使用override关键字并对maxAllowed变量进行完整声明是必需的。
	override val maxAllowed: Int = 2
}

//使用assert语句验证maxAllowed值已经成功修改了。
assert(observableButton.maxAllowed == 2, s"maxAllowed = ${observableButton.maxAllowed}")

//定义了一个观察者，以追踪当前点击数。
class ClickCountObserver extends Observer[Clickable]{
	var count = 0

	override def receiveUpdate(state: Clickable): Unit = count += 1
}

//初始化观察者实例，并将该实例:"注册"到按钮主体中。
val clickCountObserver = new ClickCountObserver
observableButton.addObserver(clickCountObserver)

val n = 5
for(i <- 1 to n) observableButton.click()

//验证观察者是否只接收到两次点击事件，其余三资点击事件都被否决了。
assert(clickCountObserver.count == 2, s"count = ${clickCountObserver.count}. Should be != $n")