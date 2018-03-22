/**
  * 《Scala程序设计》第11章：Scala对象系统2
  * 11.4 覆写抽象字段和具体字段
  *  主要是讲trait里的字段是在什么时候初始化的。
  *
  *  Time: 2018-03-21 05:30:19
  *  Author: Weidong Cao
  */
trait AbstractT2{
	println("In AbstractT2:")
	val value: Int
	//初始化inverse字段时，value值是多少？
	//尽管看上去我们是通过new AbstractT2语句创建的AbstractT2特征的一个实例，
	// 不过事实上我们实例化了一下隐式地扩展了AbstractT2特征的匿名类。
	//正如你所预计的那样，inverse变量过早被计算了。尽管没有抛出除0异常，
	//但是编译器仍认为inverse值无穷大
	val inverse = 1.0/value
	println("AbstractT2: value = " + value + ", inverse = " + inverse)
}

val obj = new AbstractT2 {
	println("In obj: ")
	override val value: Int = 10
}

println(s"obj.value = ${obj.value}, inverse = ${obj.inverse}")