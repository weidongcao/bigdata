/**
  * 《Scala程序设计》第11章Scala对象系统2
  * 11.7 对象层次结构的线性化算法。
  *
  * 线性化算法是一类用于对层级结构图 进行"扁平化"处理的算法，
  * 引入该算法是为了解决方法查找的优先级问题、构造函数调用顺序、super关键字绑定问题
  * 等一系列问题
  *
  * Scala混入多个trait实例，其中Scala将按照从右到左路的声明顺序对这些trait进行绑定。
  *
  * Time: 2018-03-22 07:27:09
  * Author: Weidong Cao
  */
class C1{
	def m = print("C1   ")
}

trait T1 extends C1{
	override def m: Unit = {
		print("T1   ")
		super.m
	}
}

trait T2 extends C1{
	override def m: Unit = {
		print("T2   ")
		super.m
	}
}

trait T3 extends C1{
	override def m: Unit = {
		print("T3   ")
		super.m
	}
}

class C2 extends T1 with T2 with T3{
	override def m: Unit = {
		print("C2   ")
		super.m
	}
}

val c2 = new C2
c2.m