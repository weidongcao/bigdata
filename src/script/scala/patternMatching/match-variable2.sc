/**
  * 除了偏函数，所有的Match语句都必须是完全覆盖所有输入的。
  * 当输入类型为Any时，在结尾用 case _ 或case some_name作为默认子句
  */
for {
	x <- Seq(1, 2, 2.7, "one", "tow", 'four)
} {
	val str = x match {
		case 1 => "int 1"
		case _: Int => "other int: " + x
		case _: Double => "a double: " + x
		case "one" => "String one"
		case _: String => "ther String: " + x
		case _ => "unexpected value: " + x
	}
	println(str)
}