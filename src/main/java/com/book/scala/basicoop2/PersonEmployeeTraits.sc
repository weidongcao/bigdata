import com.book.scala.basicoop2.{Address, Employee, Person}

val ceoAddress = Address("1 Scala Lane", "AnyTown", "CA", "98765")

val buckAddress = Address("98765")

val ceo = Employee(
	name = "Joe CEO", title = "CEO", age = Some(50),
	address = Some(ceoAddress), manager = None
)

val ceoSpouse = Person("Jane Smith", address = Some(ceoAddress))


val buck = Employee(
	name = "Buck Trends", title = "Zombie Dev", age = Some(20),
	address = Some(buckAddress), manager = Some(ceo)
)

val buckSpouse = Person("Ann Collins", address = Some(buckAddress))