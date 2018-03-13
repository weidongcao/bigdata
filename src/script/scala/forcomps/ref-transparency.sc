/**
  * 《Scala程序设计》
  * 第7章深入学习for推导式
  * Either类型有其可取之处，不过如果代码出错的话，直接招聘异常不是更简单吗？
  * 在某些时候，招聘异常当然是更合理的。招聘异常能避免对错误数据进行计算；而有时
  * 调用栈中的某些对象捕获异常可以对故障执行合理的恢复。
  * 不过抛出异常会破坏引用的透明性，
  *
  * #Time2018-03-12 07:07:08
  *
  * @param s1
  * @param s2
  * @return
  */
def addInts(s1: String, s2: String): Int =
	s1.toInt + s2.toInt

for {
	i <- 1 to 3
	j <- 1 to i
} println(s"$i + $j = ${addInts(i.toString, j.toString)}")

/**
  * 看上去我们似乎不需要调用addInts函数，只在该函数的调用处直接使用函数值即可。
  * 只是我们无法缓存之前的调用并返回命中的缓存值。但是，假如我们向addInts方法
  * 传递的字符串参数无法解析成Int值时，addInts方法会招聘异常。因此，
  * 我们不能使用函数值替代函数调用。对于某些参数列表，这些函数值无法返回。
  *
  * 更糟的是，我们无法从addInts方法的类型签名处获得该函数可能会产生垢问题。
  * 尽管这是一个精心设计的救命，但是终端用户输入的字符串必然会导致出错。
  *
  * 的确，Java提供的可检查能解决这一个特定问题。Java的方法签名能通过抛出异常暗示可能会产生错误的条件。
  * 不过由于各种原因，可检查异常实际上未能得到很好的使用。而包括Scala在内的其他语言也未能提供这一功能。
  * Java程序员也常常避免使用这一功能，改而抛出不可检查的java.lang.RuntimeException类的子类
  */
//addInts("0", "x")


/**
  * 使用Either我们可以保障引用透明性，并通过类型签名提醒调用者可能会出现错误。
  * addInts2方法的类型签名能够提示可能会出现错误。它不再通过招聘异常来捕获调用堆栈
  * 中某些应用的控制权，而是将异常作为调用堆栈中的结果值返回，以此来消除程序错误。
  *
  * 因此，假如发生了某种错误，我们可以使用Either类型来维护调用堆栈的控制权。同时，
  * 使用Either类也能使客户更清楚地理解API的行为
  *
  * #Time 2018-03-12 07:27:13
  * @param s1 字符串1
  * @param s2 字符串2
  * @return 和
  */
def addInts2(s1: String, s2: String): Either[NumberFormatException, Int] =
	try {
		Right(s1.toInt + s2.toInt)
	} catch {
		case nfe: NumberFormatException => Left(nfe)
	}

println(addInts2("1", "2"))

println(addInts2("1", "x"))
println(addInts2("x", "2"))
