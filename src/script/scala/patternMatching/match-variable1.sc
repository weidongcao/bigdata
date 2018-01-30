for {
	x <- Seq(1, 2, 2.7, "one", "two", 'four)
}{
	val str = x match {
		case 1      => "int 1"
			case i: Int => "other int: " + i
			case d: Double  => "a double: " + x
			case "one" => "String one"
			case s: String  => "other String: " + s
			case unexpected     => "unexpected value: " + unexpected
	}
	println(str)
}