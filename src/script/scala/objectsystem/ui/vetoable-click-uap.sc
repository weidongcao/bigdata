import com.book.scala.traits.observer.Observer
import com.book.scala.traits.ui.Button
import com.book.scala.traits.ui2.{Clickable, ObservableClicks}

trait VetoableClicksUAP extends Clickable {
	def maxAllowed: Int = 1

	private var count = 0

	abstract override def click() = {
		if (count < maxAllowed) {
			count += 1
			super.click()
		}
	}
}

val observableButton = new Button("Dong") with ObservableClicks with VetoableClicksUAP{
	//由于我们已经定义了maxAllowed方法，因此必须使用override关键字。
	// 假如特征休中的maxAllowed方法为抽象方法，那么override关键字便不是必须的。
	override val maxAllowed = 2
}

assert(
	observableButton.maxAllowed == 2,
	s"maxAllowed = ${observableButton.maxAllowed}"
)

class ClickCountObserver extends Observer[Clickable]{
	var count = 0
	def receiveUpdate(state: Clickable): Unit = count += 1
}

val clickCountObserver = new ClickCountObserver
observableButton.addObserver(clickCountObserver)

val n = 5
for(i<- 1 to n) observableButton.click()

assert(
	clickCountObserver.count ==2,
	s"count = ${clickCountObserver.count}. Should be != $n"
)