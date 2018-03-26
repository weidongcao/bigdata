import com.book.scala.basicoop.Person3
import com.book.scala.basicoop2.Address

val a1 = new Address("1 Scala Lane", "Anytown", "CA", "987654")
val a2 = new Address("987654")

Person3("Buck Trend1")

Person3("Buck Trends2", Some(20), Some(a1))

Person3("Buck Trends3", 20, a1)

Person3("Buck Trends4", Some(20))

Person3("Buck Trends5", 20)

Person3("Buck Trends6", address = Some(a2))

Person3("Buck Trends7", address = a2)

