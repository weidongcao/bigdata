
trait AbstractT2{
	println("In AbstractT2: ")
	val value : Int
	val inverse = 1.0 / value
	println(s"AbstractT2: value = ${value}, inverse = ${inverse}")
}

val obj = new {
	//预先初始化块中只允许出现类型定义或具体字段定义。
	val value = 10
} with AbstractT2

println(s"obj.value = ${obj.value}, inverse = ${obj.inverse}")