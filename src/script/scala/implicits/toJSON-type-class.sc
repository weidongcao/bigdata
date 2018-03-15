/**
  * 5.4 类型类模式
  * 实体转化为JSON格式的类型类演示
  *
  * 在TOJSON trait中定义了默认的缩进字符串，也定义了用于计算字段实际缩进长度的方法
  * 以及JSON对象中包含的闭合括号{...}。toJSON方法的输入参数指定了当前的缩进级别，
  * 也就是说，缩进了多少个缩进单元。由于toJSON方法要求输入这一参数客户程序就必须提供
  * 空括号或者其他缩进级别。需要注意的是输入的缩进级别是用双引号包裹的字符串值，
  * 而不是整数值
  * @param street
  * @param city
  */
case class Address(street: String, city: String)

case class Person(name: String, address: Address)

trait ToJSON{
	def toJSON(level: Int = 0): String

	val INDENTATION = "   "
	def indentation(level: Int = 0): (String, String) = (INDENTATION * level, INDENTATION * (level + 1))
}

implicit class AddressToJSON(address: Address) extends ToJSON{
	override def toJSON(level: Int = 0): String = {
		val (outdent, indent) = indentation(level)
		s"""{
		   |${indent}"street": "${address.street}",
		   |${indent}"city":   "${address.city}"
		   |$outdent}""".stripMargin
	}
}

implicit class PersonToJSON(person: Person) extends ToJSON{
	override def toJSON(level: Int = 0): String = {
		val (outdent, indent) = indentation(level)
		s"""{
		   |${indent}"name":    "${person.name}",
		   |${indent}"address": "${person.address.toJSON(level + 1)}
		   |$outdent}""".stripMargin
	}
}

val a = Address("1 Scala lane", "Anytown")
val p = Person("Wei Dong", a)
println(a.toJSON())
println()
println(p.toJSON())
