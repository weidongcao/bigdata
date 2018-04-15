import com.book.scala.traits.ui.Widget

/**
  * Created by Cao Wei Dong on 2018-03-17.
  */
class ButtonWithCallbacks(val labal: String,
                          val callbacks: List[() => Unit] = Nil) extends Widget{
	def click(): Unit = {
		updateUI()
		callbacks.foreach(f => f())
	}

	protected def updateUI(): Unit={
		/* 修改GUI页面样式 */
	}
}

object ButtonWithCallbacks{
	def apply(label: String, callback: () => Unit) =
		new ButtonWithCallbacks(label, List(callback))

	def apply(label: String) =
		new ButtonWithCallbacks(label, Nil)
}
