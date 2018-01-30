case class Address(street: String, city: String, country: String)

case class Person(name: String, age: Int, address: Address)

val alice = Person("Alice", 25, Address("1 Scala Lane", "Chicago", "USA"))
val bob = Person("Bob", 29, Address("2 Java Ave.", "Miami", "USA"))
val charlie = Person("Charlie", 32, Address("3 Python Ct.", "Boston", "USA"))

for (person <- Seq(alice, bob, charlie)) {
	person match {
		case p@Person("Alice", 25, address) => println(s"Hi Alice! $p")
		case p@Person("Bob", 29, a@Address(street, city, contry)) =>
			println(s"Hi ${p.name}! age ${p.age}, in ${p.address}")
		case p@Person(name, age, _) =>
			println(s"Who are you, $age year-old person named $name? $p")
	}
}