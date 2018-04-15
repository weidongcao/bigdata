import com.book.scala.traits.ui._
import com.book.scala.traits.observer._

class ButtonCounterObserver extends Observer[Button]{
	var count = 0

	override def receiveUpdate(state: Button): Unit = count += 1
}

val button = new ObservableButton("Click Me!")
val bco1 = new ButtonCounterObserver
val bco2 = new ButtonCounterObserver

button addObserver bco1
button addObserver bco2

(1 to 5) foreach (_ => button.click)

assert(bco1.count == 5)
assert(bco2.count == 5)