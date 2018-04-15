/**
  * 《Scala程序设计》第9章特征
  * 9.3 可堆叠的特征
  *
  * Time 2018-03-17 08:04:27
  * Author Weidong Cao
  */

import com.book.scala.traits.observer.Observer
import com.book.scala.traits.ui2.{Button, Clickable, ObservableClicks}

val button = new Button("Click Me!") with ObservableClicks

class ClickCountObserver extends Observer[Clickable]{
	var count = 0

	override def receiveUpdate(state: Clickable): Unit = count += 1
}

val bco1 = new ClickCountObserver
val bco2 = new ClickCountObserver

button addObserver bco1
button addObserver bco2

(1 to 5) foreach(_ => button.click())

assert(bco1.count == 5, s"bco1.count ${bco1.count} != 5")
assert(bco2.count == 5, s"bco2.count ${bco2.count} != 5")
println("Success!")