case class Address(street: String, city: String, country: String)

case class Person(name: String, age: Int, address: Address)

val alice = Person("Alice", 25, Address("1 Scala Lane", "Chicago", "USA"))
val bob = Person("Bob", 29, Address("2 Java Ave.", "Miami", "USA"))
val charlie = Person("Charlie", 32, Address("3 Pythoon Ct.", "Boston", "USA"))

for (person <- Seq(alice, bob, charlie)) {
	person match {
		case Person("Alice", 25, Address(_, "Chicago", _)) => println("Hi Alice")
		case Person("Bob", 29, Address("2 Java Ave.", "Miami", "USA")) => println("Hi Bob")
		case Person(name, age, _) => println(s"Who are you, $age year-old person named $name?")
	}
}