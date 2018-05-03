import com.book.scala.selftype.ButtonClickObserver
import com.book.scala.selftype.ButtonSubjectObserver.ObservableButton

val buttons = Vector(new ObservableButton("oen"), new ObservableButton("Two"))
val observer = new ButtonClickObserver

buttons foreach(_.addObserver(observer))

for(i <- 0 to 2) buttons(0).click()
for(i <- 0 to 4) buttons(1).click()

println(observer.clicks)