/**
  * case y的含义其实是：匹配所有输入（由于这里没有类型注解），
  * 并将其赋值给新的变量y，这里的y没有被解释为方法参数y。因此，
  * 事实上我们将一个默认的，匹配一切的语句写在了第一个，导致了
  * 系统给出了这条"变量型匹配语句“会匹配一切输入的警告。
  * @param y
  */
def checkY(y: Int) = {
	for {
		x <- Seq(99, 100, 101)
	} {
		val str = x match {
			case `y` => "found y!"
			case i: Int => "int: " + i
		}
		println(str)
	}
}

checkY(100)