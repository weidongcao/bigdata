/**
  * 5.5 隐式所导致的技术问题
  *
  * 当隐式特征与其他Scala特征，尤其是子类型特征发生交集时，会产生一些技术问题，下面是一个示例
  *
  * 我们定义了一个名为Stringizer的抽象体。如果按照之前ToJSON救命的做法，我们会为所有我们希望
  * 能字符串化的类型创建隐式类。这本身就是一个问题。如果我们希望处理一组不同的类型实例，
  * 我们只能在List类型的map方法内隐式地传入一个Stringizer实例。因此，我们就必须定义一个AnyStringerizer
  * 类，该类知道如何对我们书籍的所有类型进行处理。这些类型甚至还包含用于招聘异常的default子句。
  *
  * 这种实现方式非常不美观，同时也违背了面向对象编程的一条核心规则——你不应该使用switch语句对可能发生
  * 变化的类型进行判断。相反，你应该利用多态颁发任务这类似于toString方法在Scala和Java语言中的动作方式。
  *
  * 无论何时都要为隐式转换方法指定返回类型。否则类型推导推断出的返回类型可能会导致预料之外的结果
  * 另外，虽然编译器会执行一些“方便”用户的转换。但是目前来看这些转换带来的麻烦多于益处
  * @tparam T
  */

trait Stringizer[+T]{
	def stringize: String
}

implicit class AnyStringizer(a: Any) extends Stringizer[Any]{
	def stringize: String = a match {
		case s: String => s
			case i: Int => (i * 10).toString
			case f: Float => (f * 10.1).toString
			case other =>
				throw new UnsupportedOperationException(s"Can't stringize $other")
	}
}

val list: List[Any] = List(1, 2.2F, "three", 'symbol)
list foreach{ (x:Any) =>
	try{
		println(s"$x: ${x.stringize}")
	}catch {
		case e: java.lang.UnsupportedOperationException => println(e)
	}
}