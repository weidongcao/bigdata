import com.book.scala.traits.observer.Observer
import com.book.scala.traits.ui2.{Button, Clickable, ObservableClicks, VetoableClicks}

/**
  *《Scala程序设计》第9章：特征
  * 9.3 可堆叠特征
  * 使用ObservableClicks和VetoableClicks特征的对象中该值重新设置为2
  *
  * Time： 2018-03-18 05:47:16
  * Author Weidong Cao
  */
val button =
	new Button("Click Me!") with ObservableClicks with VetoableClicks{
		//覆写maxAllowed的值，将值设置为2
		override val maxAllowed: Int = 2
	}

class ClickCountObserver extends Observer[Clickable] {
	var count = 0
	override def receiveUpdate(state: Clickable): Unit = count += 1
}

val bco1 = new ClickCountObserver
val bco2 = new ClickCountObserver

button addObserver bco1
button addObserver bco2

(1 to 5) foreach(_ => button.click())

assert(bco1.count == 2, s"bco1.count ${bco1.count} != 2")
assert(bco2.count == 2, s"bco2.count ${bco2.count} != 2")

println("Success!")
