
/**
  * 4.12 模式匹配的其他用法
  * 模式匹配与case语句的另一个使得用法是，它们可以使带参数的函数字面量更易于使用
  */
case class Address(street: String, city:String, country:String)
case class Person(name:String, age:Int)

val as = Seq(
	Address("1 Scala Lane", "Anytown", "USA"),
	Address("2 Clojure Lane", "Othertown", "USA")
)

val ps = Seq(
	Person("Buck Trends", 29),
	Person("Clo Jure", 28)
)

val pas = ps zip as

//不太美观的方法
pas map {tup=>
	val Person(name, age) = tup._1
	val Address(street, city, country) = tup._2
	println(s"$name (age: $age) lives at $street, $city, in $country")
	s"$name (age: $age) lives at $street, $city, in $country"
}

//不错的方法
pas map{
	case (Person(name, age), Address(street, city, country)) =>
		println(s"$name (age: $age) lives at $street, $city in $country")
		s"$name (age: $age) lives at $street, $city in $country"
}