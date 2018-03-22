import scalaz._, std.AllInstances._

/**
  * 《Scala程序设计》第7章——深入学习for推导式
  * 对用户名进行验证，用户名必须非空并且只能包含字母
  *
  * #Time 2018-03-14 07:06:55
  * @param key
  * @param name
  * @return
  */
def validName(key: String, name: String): Validation[List[String], List[(String, Any)]] = {
	val n = name.trim
	if (n.length > 0 && n.matches("""^[a-zA-Z]+$""")) Success(List(key -> n))
	else Failure(List(s"Invalid $key <$n>"))
}

/**
  * 验证字符串能否转换为大于0的整数
  */
def positive(key: String, n: String):
	Validation[List[String], List[(String, Any)]] = {
	try{
		val i = n.toInt
		if(i >0) Success(List(key -> i))
		else Failure(List(s"$n is not an integer"))
	} catch {
		case _: java.lang.NumberFormatException =>
				Failure(List(s"$n is not an Integer"))
	}
}

def validateForm(firstName: String, lastName: String, age: String):
	Validation[List[String], List[(String, Any)]] = {
	validName("first-name", firstName) +++ validName("last-name", lastName) +++ positive("age",age)
}

validateForm("Dean", "Wampler", "29")

validateForm("", "Wampler", "29")

validateForm("D e a n", "Wampler", "29")

validateForm("D1e2a3n_", "Wampler", "29")

validateForm("Dean", "", "29")

validateForm("Dean", "Wampler", "0")

validateForm("Dean", "Wampler", "xx")

validateForm("", "Wampler", "0")

validateForm("Dean", "", "0")

validateForm("D e a n", "", "29")


