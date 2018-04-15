/**
  * 假设我们只需要一个ObservableButton实例，那么我们并不需要单独声明一个混入了我们所需trait的类，
  * 只需要声明一个Button对象，并凭空为这个对象注入Subject特征即可。
  *
  * Time：2018-03-17 07:22:18
  * Author：Weidong Cao
  */

import com.book.scala.traits.observer.{Observer, Subject}
import com.book.scala.traits.ui.Button
val button = new Button("Click Me!") with Subject[Button]{
	override def click(): Unit = {
		super.click()
		notifyObservers(this)
	}
}

class ButtonCountObserver extends Observer[Button]{
	var count = 0
	def receiveUpdate(state: Button): Unit = count +=1
}

val bco1 = new ButtonCountObserver
val bco2 = new ButtonCountObserver

button addObserver bco1
button addObserver bco2

(1 to 5) foreach (_ => button.click )

/**
  * 与之前的实现不同，我们在声明Button()对象时未声明新类，而是直接捎带上了Subject[Button]实例。
  * 这与Java中初始化某一实现了接口的匿名类的做法较为相似
  * 但Scala提供了更多的灵活性。
  */
assert(bco1.count == 5)
assert(bco2.count == 5)
println("Success!")

