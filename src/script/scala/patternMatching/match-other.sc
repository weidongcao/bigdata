/**
  * 4.12 模式匹配的其他用法
  * 在定义变量时也可以运用模式匹配，包括for表达式中的变量定义
  * @param street
  * @param city
  * @param country
  */
case class Address(street: String, city: String, country: String)

case class Person(name: String, age: Int, address: Address)
val Person(name, age, Address(_, state, _)) =
	Person("Dean", 29, Address("1 Scala Way", "CA", "USA"))

val head1 +: tail1 = List(1,2,3,4)

val head2 +: head21 +: tail2 = Vector(1,2,3,4)

val Seq(a, b, c) = List(1, 2, 3, 4, 5)